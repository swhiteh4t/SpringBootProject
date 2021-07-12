package main.java.com.repository;

import org.springframework.data.repository.CrudRepository;

import main.java.com.entity.Client;


public interface ClientRepo extends CrudRepository<Client, String> {

}
