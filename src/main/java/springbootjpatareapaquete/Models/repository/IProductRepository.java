package springbootjpatareapaquete.Models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springbootjpatareapaquete.Models.entity.Productos;
@Repository
public interface IProductRepository extends JpaRepository<Productos, Long>{
@Transactional
@Modifying
@Query("Delete from Productos p where p.id = ?1")
public void deleteProductById(Long id);
/*@Query("Update Productos p SET p.nombre where")*/	

/*
@Transactional
@Modifying
@Query("Update Productos p Set p.nombre where p.id = ?1")
public void updateProductName(String name);
*/

@Transactional
@Modifying
@Query("Update Productos p SET p.estado = ?1 where p.id = ?2")
public void updateProductStatus(boolean status, Long id);

@Transactional
@Query("SELECT sum(p.precio) FROM Productos p")
public float sumaPreciosProd();

@Query("select p from Productos p where p.nombre like %?1%")
public Page<Productos> findByNombre(String nombre, Pageable pageable);


}


