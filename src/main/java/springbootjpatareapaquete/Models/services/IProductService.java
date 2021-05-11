package springbootjpatareapaquete.Models.services;

import java.util.List;

import org.springframework.data.domain.Page;

import springbootjpatareapaquete.Models.entity.Productos;

public interface IProductService {
public Page<Productos> findAllProducts(int pageNumber, String nombre);
public Productos findbyid(Long id);
public void saveProduts(Productos products);
public void editEstado(boolean status,Long id);
public Productos detailProduct(Long id);
public void deleteById(Long id);
public Long cantidadProd();
public float sumaProd();
}

