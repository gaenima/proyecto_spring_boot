package springbootjpatareapaquete.Models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
public class Productos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="Complete el nombre del producto")
	@Size(min=4, max=20,message="El nombre debe contener entre 4 y 20 caracteres")
	@Column(length = 40, nullable = false)
	private String nombre;
	
	@NotNull(message="Complete la cantidad de productos")
	@Column(nullable = false)
	private int cantidad;
	
	@NotNull(message="Complete el precio del producto")
	@Column(nullable = false)
	private float precio;
	
	@NotBlank(message="Complete la categoría del producto")
	@Column(length = 40, nullable = false)
	private String categoria;
	
	private boolean estado;
	@ManyToOne()
	private Categoria category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	

}
