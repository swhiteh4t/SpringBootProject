package main.java.com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import main.java.com.entity.Material;


public interface MaterialRepo extends PagingAndSortingRepository<Material, Long> {

}
