package com.jsondecoder.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsondecoder.domain.CHObject;
import com.jsondecoder.utilities.FileFinder;

public class CHObjectRepositoryTest {

    private EmbeddedDatabase db;

    @Autowired
	CHObjectRepository chObjectDao;
    
    @Before
    public void setUp() {
    	
    	// Leer de JSON
    	List<CHObject> chObjects = readJSON();
    	System.out.println(chObjects.size());
    	
    	// Inicializar H2 DB
    	db = new EmbeddedDatabaseBuilder()
    		.setType(EmbeddedDatabaseType.H2)
    		.addScript("db/sql/create-db.sql")
    		.addScript("db/sql/insert-data.sql")
    		.build();

    	// Ejecutar tests
    }
    
    @Test
    public void testFindByname() {

    	
    	CHObject cHObject = chObjectDao.findById(68268203);
  
    	Assert.assertNotNull(cHObject);
    	Assert.assertEquals(68268203, cHObject.getId().intValue());
    	System.out.println(cHObject);
//    	Assert.assertEquals("mkyong@gmail.com", cHObject.getEmail());

    }

    @After
    public void tearDown() {
        db.shutdown();
    }
    
    private List<CHObject> readJSON(){
    	List<CHObject> result = new ArrayList<CHObject>();
    	
//    	System.out.println("processing CHObject file " + args[0] +"\n");
		
		String chobjectsDirectory = "D:\\Development\\GitHub\\CHcollection\\objects\\682\\682\\";
		
		List<Path> files;
		try {
			files = FileFinder.getFileList(chobjectsDirectory, "*.json");
			
			for (Path f : files) {
		        
		        File chobjectFile = new File(f.toString());
		        try {
					CHObject chobject = new ObjectMapper().readValue(chobjectFile, CHObject. class);
					System.out.println("\n" + chobject.toString());
					result.add(chobject);
					
				} catch (JsonParseException e) {
					System.out.println(e.getLocalizedMessage());
					System.out.println(e.getMessage());
					System.out.println("Error parsing the file.");
				} catch (JsonMappingException e) {
					System.out.println(e.getLocalizedMessage());
					System.out.println(e.getMessage());
					System.out.println("Error mapping to Java object.");
				} catch (IOException e) {
					System.out.println(e.getLocalizedMessage());
					System.out.println(e.getMessage());
					System.out.println("Unknown I/O error.");
				}
			}
			
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println("Unknown I/O error.");
		}
		return result;
    }

}