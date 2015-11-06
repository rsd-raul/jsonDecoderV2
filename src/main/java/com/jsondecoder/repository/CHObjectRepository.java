package com.jsondecoder.repository;

import java.util.List;

import com.jsondecoder.domain.CHObject;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;

public interface CHObjectRepository extends CrudRepository<CHObject, Integer>{
	
	public List<CHObject> findByTitle(String title); // SELECT * FROM artists WHERE gender = 'xxxxx'
//	public List<CHObject> findByFullNameAndGender(String string, String string2);
//
//	public List<CHObject> findByFullName(String fullName); // SELECT * FROM artists WHERE fullName LIKE '%xxxxx%'
//	public List<CHObject> findByFullNameContains(String fullName); // SELECT * FROM artists WHERE fullName LIKE '%xxxxx%'
//	public List<CHObject> findByFullNameContainsIgnoreCase(String fullName); // SELECT * FROM artists WHERE LOWER(fullName) LIKE LOWER('%xxxxx%')
//
//	public List<CHObject> findByMovementsId(int id);
//	
//	@Query("SELECT a FROM CHObject a JOIN a.movements m WHERE m.name = :name")
//	public List<CHObject> findByMovementsName(@Param("name") String name);
//	
//	public int countByGender(String gender);
//	
//	@Query(value="Select * FROM artists WHERE id = :id", nativeQuery = true)
//	public CHObject findByIdNative(@Param("id") int id);
}
