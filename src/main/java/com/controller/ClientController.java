package main.java.com.controller;

import java.net.URI;
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

import main.java.com.entity.Client;
import main.java.com.repository.ClientRepo;



@RestController
public class ClientController {

	@Autowired
	private ClientRepo clientRepository;

	@GetMapping("/clients")
	public List<Client> retrieveAllclients() {
		return (List<Client>) clientRepository.findAll();
	}

	@GetMapping("/clients/{id}")
	public Client retrieveclient(@PathVariable String id) {
		Optional<Client> client = clientRepository.findById(id);

		if (!client.isPresent())
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Client Not Found");

		return client.get();
	}

	@DeleteMapping("/clients/{id}")
	public void deleteclient(@PathVariable String id) {
		clientRepository.deleteById(id);
	}

	@PostMapping("/clients")
	public ResponseEntity<Object> createclient(@RequestBody Client client) {
		Client savedclient = clientRepository.save(client);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedclient.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/clients/{id}")
	public ResponseEntity<Object> updateclient(@RequestBody Client client, @PathVariable String id) {

		Optional<Client> clientOptional = clientRepository.findById(id);

		if (!clientOptional.isPresent())
			return ResponseEntity.notFound().build();

		client.setId(id);
		
		clientRepository.save(client);

		return ResponseEntity.noContent().build();
	}
}