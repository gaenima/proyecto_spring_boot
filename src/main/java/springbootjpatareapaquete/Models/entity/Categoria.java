package springbootjpatareapaquete.Models.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 private String category;
 @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL )
 private Productos producto;
 
}
