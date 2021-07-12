package main.java.com.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.xml.txw2.annotation.XmlElement;

@Entity
@Table(name="MATERIAL")
@XmlRootElement(name = "material")
public class Material {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="stock")
	private BigDecimal stock;
	
	@OneToMany(targetEntity=MaterialUsage.class, mappedBy="material",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference(value="Material_usage")
	@JsonIgnore
	@Column(name="material_usage")
	private Set<MaterialUsage> materialUsage;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getStock() {
		return stock;
	}

	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}
	
	@JsonIgnore
	public Set<MaterialUsage> getMaterialUsage() {
		return materialUsage;
	}

	public void setMaterialUsage(Set<MaterialUsage> materialUsage) {
		this.materialUsage = materialUsage;
	}

	public Material(Long id, String name, BigDecimal price, BigDecimal stock, Set<MaterialUsage> materialUsage) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.materialUsage = materialUsage;
	}

	public Material() {
	}

	
	

}
