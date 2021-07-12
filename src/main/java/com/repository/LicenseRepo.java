package main.java.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import main.java.com.entity.License;


public interface LicenseRepo extends CrudRepository<License, String> {
	

	//Get all the licenses issued by a particular license issuer
    @Query("select l from License l where l.issuer like :licenseIssuer")
    List<License> findLicenseByIssuer(@Param("licenseIssuer") String licenseIssuer);

}
