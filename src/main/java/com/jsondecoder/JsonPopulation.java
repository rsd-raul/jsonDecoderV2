package com.jsondecoder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsondecoder.domain.CHObject;
import com.jsondecoder.repository.CHObjectDao;
import com.jsondecoder.utilities.FileFinder;

@SpringBootApplication
public class JsonPopulation implements CommandLineRunner {

//	@Autowired
//	JdbcTemplate jdbcTemplate;
	@Autowired
	CHObjectDao chObjectDao;
	
	public void query06() {

		// Get chObject #1
		CHObject chObject = chObjectDao.getById(68268203);
		System.out.println("Before: " + chObject.toString());
		
		// CHObject went through gender reassignment surgery
		chObject.setDescription("ttteeessst");
		chObjectDao.save(chObject);
		
		// Get the chObject again to ensure change was saved
		chObject = chObjectDao.getById(68268203);
		System.out.println("After: " + chObject.toString());
	}
	
	private void insertOnH2(CHObject chObject){
		chObjectDao.add(chObject);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		EmbeddedDatabase db = buildDatabase();
		List<CHObject> chObjects = decodeJson();
		
		System.out.println(chObjectDao.findAll().size());
		for( CHObject aux : chObjects)
			insertOnH2(aux);
		System.out.println(chObjectDao.findAll().size());
		
//		System.out.println(chObjectDao.getById(68268203));
//		System.out.println(db.hashCode());
//		query06();  // Using a DAO class
	}
	
    public static void main(String[] args) {
        SpringApplication.run(JsonPopulation.class, args);
    }

    
    private EmbeddedDatabase buildDatabase(){
    	EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
						    		.setType(EmbeddedDatabaseType.H2)
						    		.addScript("db/sql/create-db.sql")
//						    		.addScript("db/sql/insert-data.sql")
						    		.build();
		return db;
    }
    
    private List<CHObject> decodeJson() throws IOException{
    	List<CHObject> result = new ArrayList<CHObject>();
    	
		String chobjectsDirectory = "D:\\Development\\GitHub\\CHcollection\\objects\\682\\682\\";
		
		List<Path> files = FileFinder.getFileList(chobjectsDirectory, "*.json");
			
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
		
		return result;
    }
}
