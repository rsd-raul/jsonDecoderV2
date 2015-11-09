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
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsondecoder.domain.CHObject;
import com.jsondecoder.repository.CHObjectRepository;
import com.jsondecoder.repository.ImageRepository;
import com.jsondecoder.repository.ParticipantRepository;
import com.jsondecoder.repository.ParticipationRepository;
import com.jsondecoder.repository.RoleRepository;
import com.jsondecoder.service.CHObjectService;
import com.jsondecoder.utilities.FileFinder;

@Import(DefaultConfig.class)
@SpringBootApplication
public class JsonPopulation implements CommandLineRunner {
	
	@Autowired
	CHObjectRepository chObjectRepository;
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	ParticipantRepository participantRepository;
	@Autowired
	ParticipationRepository participationRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	CHObjectService chObjectService;
	
	@Override
	public void run(String... arg0) throws Exception {
		List<CHObject> chObjects;
		
		buildDatabase();
		
		chObjects = decodeJson();
		
		getDatabaseStatus("BEFORE");

		populateDatabase(chObjects);
		
		getDatabaseStatus("AFTER");
	}
	
	private void getDatabaseStatus(String status){
		System.out.println(	"Database status " 	+ status + " population \n" + 
							" Objects: " 		+ chObjectRepository.findAll().size() +
							" Images: " 		+ imageRepository.findAll().size() +
							" Participants: " 	+ participantRepository.findAll().size() +
							" Roles: " 			+ roleRepository.findAll().size() +
							" Particpations: " 	+ participationRepository.findAll().size());
	}
	
	private void populateDatabase(List<CHObject> chObjects){
		for( CHObject aux : chObjects)
			chObjectService.save(aux);
	}
    
    private EmbeddedDatabase buildDatabase(){
    	EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
						    		.setType(EmbeddedDatabaseType.H2)
						    		.addScript("db/sql/create-db.sql")
						    		.build();
		return db;
    }
    
    private List<CHObject> decodeJson() throws IOException{
    	List<CHObject> result = new ArrayList<CHObject>();
    	
		String chobjectsDirectory = "D:\\Development\\GitHub\\CHcollection\\objects\\682\\682\\";
		
		List<Path> files = FileFinder.getFileList(chobjectsDirectory, "*.json");
			
		for (Path f : files) {
			
	        File chObjectFile = new File(f.toString());
	        try {
				CHObject chobject = new ObjectMapper().readValue(chObjectFile, CHObject. class);
				result.add(chobject);
				
			} catch (JsonParseException e) {
				System.out.println("Error parsing the file.");
			} catch (JsonMappingException e) {
				System.out.println("Error mapping to Java object.");
			} catch (IOException e) {
				System.out.println("Unknown I/O error.");
			}
	        
		}
		return result;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(JsonPopulation.class, args);
    }
}
