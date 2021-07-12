package main.java.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import main.java.com.entity.MaterialUsage;


public interface MaterialUsageRepo extends CrudRepository<MaterialUsage, Long> {

	@Query("select m from MaterialUsage m where quantity >= :q")
	List<MaterialUsage> materialUsageGreatherThan(@Param("q") String q);
}
