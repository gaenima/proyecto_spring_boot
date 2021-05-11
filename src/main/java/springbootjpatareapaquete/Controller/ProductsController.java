package springbootjpatareapaquete.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springbootjpatareapaquete.Models.entity.Productos;
import springbootjpatareapaquete.Models.services.IProductService;

@Controller
public class ProductsController {
	@Autowired
	private IProductService iproductserv;

	@GetMapping("/listadopord")
	public String listarProducts(Model model) {
		//model.addAttribute("productosM", iproductserv.findAllProducts());
		return paginationProducts(1,null,model);
	}

	@GetMapping("/pagina/{pageNumber}")
	public String paginationProducts(@PathVariable(name = "pageNumber") int paginaactual, @RequestParam(value="nombre", required=false) String nombre, Model model) {
		Page<Productos> page = iproductserv.findAllProducts(paginaactual, nombre);
		long totalitems=page.getTotalElements();
		int totalpages=page.getTotalPages();
		List<Productos> listaProductos = page.getContent();
		model.addAttribute("productosM", listaProductos);
		model.addAttribute("totalitems", totalitems);
		model.addAttribute("totalpages", totalpages);
		model.addAttribute("paginaactual", paginaactual);
		return "listar";
	}

	@GetMapping("/form/crear")
	public String fromulario(Model model) {
		model.addAttribute("product", new Productos());
		return "formulario";
	}

	@PostMapping("/guardarprod")
	public String guardarProducto(@Valid @ModelAttribute("product") Productos products, BindingResult result)

	{
		if (result.hasErrors()) {
			return "formulario";
		}
		iproductserv.saveProduts(products);
		return "redirect:/listadopord";

	}

	@GetMapping("/editproduct/{idproduct}")
	public String editarProducto(@PathVariable(name = "idproduct") Long idproduct, Model model) {
		Productos prod = iproductserv.findbyid(idproduct);
		if (prod == null) {
			return "redirect:/listadopord";
		}
		model.addAttribute("product", prod);
		return "formulario";
	}

	@GetMapping("/deleteprod/{id}")
	public String eliminarProducto(@PathVariable(name = "id") Long id) {
		if (id > 0) {
			iproductserv.deleteById(id);
		}
		return "redirect:/listadopord";
	}

	@GetMapping("/editarProdEstado/{status}/{idproduct}")
	public String editarProdEstado(@PathVariable(name = "status") boolean status,
			@PathVariable(name = "idproduct") Long idproduct) {
		iproductserv.editEstado(status, idproduct);
		return "redirect:/listadopord";
	}

	@GetMapping("/datalleProd/{idprod}")
	public String detalleProductos(Model model, @PathVariable(name = "idprod") Long idprod) {
		Productos prod = iproductserv.detailProduct(idprod);
		if (prod == null) {
			return "redirect:/listadopord";
		}
		model.addAttribute("product", prod);
		return "detalleProd";
	}

	@GetMapping("/")
	public String homePage(Model model) {

		model.addAttribute("homeModel", iproductserv.cantidadProd());
		model.addAttribute("hmodel", iproductserv.sumaProd());
		return "home";
	}

}
