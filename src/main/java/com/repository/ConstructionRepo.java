package main.java.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import main.java.com.entity.Construction;
import main.java.com.entity.License;


public interface ConstructionRepo extends CrudRepository<Construction, Long> {
	
	@Query("select c from Construction c where c.state like 'License not approved'")
    List<Construction> getConstructionsWithOutLicense();
}
