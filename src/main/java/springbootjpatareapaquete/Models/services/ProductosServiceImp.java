package springbootjpatareapaquete.Models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springbootjpatareapaquete.Models.entity.Productos;
import springbootjpatareapaquete.Models.repository.IProductRepository;

@Service
public class ProductosServiceImp implements IProductService {
	@Autowired
	private IProductRepository iproductrepo;

	@Override
	@Transactional(readOnly = true)
	public Page<Productos> findAllProducts(int pageNumber, String nombre) {
		Pageable pageable = PageRequest.of(pageNumber -1, 5);
		if(nombre != null) {
		 return	iproductrepo.findByNombre(nombre, pageable);
		}
		return iproductrepo.findAll(pageable);
	}

/*	public void saveProduts() {

	}
*/
	@Override
	@Transactional
	public void saveProduts(Productos products) {
		products.setEstado(true);
		iproductrepo.save(products);

	}

	@Transactional(readOnly = true)
	public Productos findbyid(Long id) {
		return iproductrepo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		iproductrepo.deleteProductById(id);

	}

	@Transactional
	public void editEstado(boolean status,Long id) { 
	     iproductrepo.updateProductStatus(status,id);
	}

	@Override
	@Transactional(readOnly = true)
	public Productos detailProduct(Long id) {
		return iproductrepo.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Long cantidadProd() {
		return iproductrepo.count();		 
	}

	@Override
	public float sumaProd() {
		return iproductrepo.sumaPreciosProd();		
	}
	
}
