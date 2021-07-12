package main.java.com.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import main.java.com.entity.Material;
import main.java.com.repository.MaterialRepo;

@RestController
public class MaterialController {

	@Autowired
	private MaterialRepo materialRepository;

	@GetMapping("/materials")
	public List<Material> retrieveAllmaterials() {
		return (List<Material>) materialRepository.findAll();
	}
	
	//Retrieve the elements with less stock
	@GetMapping("/materials/min")
	public List<Material> retrieve3MostScarcematerials() {
		Pageable page = PageRequest.of(0, 3, Sort.by("stock").descending());
		Page<Material> pageMaterial = materialRepository.findAll(page);
		
		return (List<Material>) pageMaterial.getContent();
	}
	
	@GetMapping("/materials/{id}")
	public Material retrievematerial(@PathVariable Long id) {
		Optional<Material> construction = materialRepository.findById(id);

		if (!construction.isPresent())
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Material Not Found");

		return construction.get();
	}

	@DeleteMapping("/materials/{id}")
	public void deletematerial(@PathVariable Long id) {
		materialRepository.deleteById(id);
	}

	@PostMapping("/materials")
	public ResponseEntity<Object> creatematerial(@RequestBody Material material) {
		Material savedMaterial = materialRepository.save(material);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedMaterial.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/materials/{id}")
	public ResponseEntity<Object> updatematerial(@RequestBody Material material, @PathVariable Long id) {

		Optional<Material> constructionOptional = materialRepository.findById(id);

		if (!constructionOptional.isPresent())
			return ResponseEntity.notFound().build();

		material.setId(id);
		
		materialRepository.save(material);

		return ResponseEntity.noContent().build();
	}
}