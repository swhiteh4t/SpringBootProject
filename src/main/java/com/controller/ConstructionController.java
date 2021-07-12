package main.java.com.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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
import main.java.com.repository.ConstructionRepo;

@RestController
public class ConstructionController {

	@Autowired
	private ConstructionRepo constructionRepository;

	@GetMapping("/constructions")
	public List<Construction> retrieveAllconstructions() {
		return (List<Construction>) constructionRepository.findAll();
	}
	
	@GetMapping("/constructions/inactive")
	public List<Construction> retrieveAllconstructionsInactive() {
		return (List<Construction>) constructionRepository.getConstructionsWithOutLicense();
	}
	
	
	@GetMapping("/constructions/{id}")
	public Construction retrieveconstruction(@PathVariable Long id) {
		Optional<Construction> construction = constructionRepository.findById(id);

		if (!construction.isPresent())
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Construction Not Found");

		return construction.get();
	}

	@DeleteMapping("/constructions/{id}")
	public void deleteconstruction(@PathVariable Long id) {
		constructionRepository.deleteById(id);
	}

	@PostMapping("/constructions")
	public ResponseEntity<Object> createconstruction(@RequestBody Construction construction) {
		Construction savedconstruction = constructionRepository.save(construction);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedconstruction.getCode().toString()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/constructions/{id}")
	public ResponseEntity<Object> updateconstruction(@RequestBody Construction construction, @PathVariable Long id) {

		Optional<Construction> constructionOptional = constructionRepository.findById(id);

		if (!constructionOptional.isPresent())
			return ResponseEntity.notFound().build();

		construction.setCode(id);
		
		constructionRepository.save(construction);

		return ResponseEntity.noContent().build();
	}
}