package main.java.com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="MATERIAL_USAGE")
@XmlRootElement(name = "material_usage")
public class MaterialUsage {
	@Id
	private Long id;

	@ManyToOne
    @JoinColumn(name="material_usage", nullable=false)
	@JsonBackReference(value="Material_usage")
	@JsonProperty("material_id")
    private Material material;
	
	@ManyToOne
    @JoinColumn(name="id_usage", nullable=false)
	@JsonBackReference(value="Material_construction")
	@JsonProperty("construction_id")
    private Construction construction;
	
	@Column(name="quantity")
	private Long quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Construction getConstruction() {
		return construction;
	}

	public void setConstruction(Construction construction) {
		this.construction = construction;
	}

	public MaterialUsage(Long id, Material material, Construction construction) {
		this.id = id;
		this.material = material;
		this.construction = construction;
	}

	public MaterialUsage() {
	}
	
	
}
