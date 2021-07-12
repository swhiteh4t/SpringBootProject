package main.java.com.controller;

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

import main.java.com.entity.License;
import main.java.com.repository.LicenseRepo;

@RestController
public class LicenseController {

	@Autowired
	private LicenseRepo licenseRepository;

	@GetMapping("/licenses")
	public List<License> retrieveAlllicenses() {
		return (List<License>) licenseRepository.findAll();
	}
	
	@GetMapping("/licenses_issuer/{id}")
	public List<License> retrieveAlllicensesByIssuer(@PathVariable String id) {
		return (List<License>) licenseRepository.findLicenseByIssuer(id);
	}

	@GetMapping("/licenses/{id}")
	public License retrievelicense(@PathVariable String id) {
		Optional<License> license = licenseRepository.findById(id);

		if (!license.isPresent())
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "License Not Found");

		return license.get();
	}

	@DeleteMapping("/licenses/{id}")
	public void deletelicense(@PathVariable String id) {
		licenseRepository.deleteById(id);
	}

	@PostMapping("/licenses")
	public ResponseEntity<Object> createlicense(@RequestBody License license) {
		License savedLicense = licenseRepository.save(license);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedLicense.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/licenses/{id}")
	public ResponseEntity<Object> updatelicense(@RequestBody License license, @PathVariable String id) {

		Optional<License> licenseOptional = licenseRepository.findById(id);

		if (!licenseOptional.isPresent())
			return ResponseEntity.notFound().build();

		license.setId(id);
		
		licenseRepository.save(license);

		return ResponseEntity.noContent().build();
	}
	
	
}