package main.java.com.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import main.java.com.entity.Construction;
import main.java.com.entity.Material;
import main.java.com.entity.MaterialUsage;
import main.java.com.entity.Schedule;
import main.java.com.repository.ConstructionRepo;
import main.java.com.repository.MaterialRepo;
import main.java.com.repository.MaterialUsageRepo;

@RestController
public class MaterialUsageController {

	@Autowired
	private MaterialUsageRepo materialusageRepository;
	@Autowired
	private ConstructionRepo constructionRepo;
	@Autowired
	private MaterialRepo materialRepo;

	@GetMapping("/materialusages")
	public List<MaterialUsage> retrieveAllmaterialusages() {
		return (List<MaterialUsage>) materialusageRepository.findAll();
	}
	
	@GetMapping("/materialusages/quantity/{q}")
	public List<MaterialUsage> retrieveAllmaterialusagesByQuanatity(@PathVariable String q) {
		return (List<MaterialUsage>) materialusageRepository.materialUsageGreatherThan(q);
	}

	@GetMapping("/materialusages/{id}")
	public MaterialUsage retrievematerialusage(@PathVariable Long id) {
		Optional<MaterialUsage> construction = materialusageRepository.findById(id);

		if (!construction.isPresent())
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "MaterialUsage Not Found");

		return construction.get();
	}
	

	@DeleteMapping("/materialusages/{id}")
	public void deletematerialusage(@PathVariable Long id) {
		materialusageRepository.deleteById(id);
	}

	@PostMapping("/materialusages")
	public ResponseEntity<Object> creatematerialusage(@RequestBody MaterialUsageRequest req) {
		Optional<Construction> c = constructionRepo.findById(req.getId_construction());
		Optional<Material> m = materialRepo.findById(req.getId_material());
		if (!c.isPresent() || !m.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule Not Found");

		MaterialUsage savedMaterialUsage = materialusageRepository.save(new MaterialUsage(req.getId(),m.get(),c.get()));
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedMaterialUsage.getId().toString()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/materialusages/{id}")
	public ResponseEntity<Object> updatematerialusage(@RequestBody MaterialUsage materialusage, @PathVariable Long id) {

		Optional<MaterialUsage> constructionOptional = materialusageRepository.findById(id);

		if (!constructionOptional.isPresent())
			return ResponseEntity.notFound().build();

		materialusage.setId(id);
		
		materialusageRepository.save(materialusage);

		return ResponseEntity.noContent().build();
	}
}


class MaterialUsageRequest{
	private Long id;
	private Long id_material;
	private Long id_construction;
	private BigDecimal quantity;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId_material() {
		return id_material;
	}
	public void setId_material(Long id_material) {
		this.id_material = id_material;
	}
	public Long getId_construction() {
		return id_construction;
	}
	public void setId_construction(Long id_construction) {
		this.id_construction = id_construction;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public MaterialUsageRequest(Long id,Long id_material, Long id_construction, BigDecimal quantity) {
		super();
		this.id_material = id_material;
		this.id_construction = id_construction;
		this.quantity = quantity;
	}
	
	public MaterialUsageRequest() {}
}