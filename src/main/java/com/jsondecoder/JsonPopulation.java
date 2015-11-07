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
		
		buildDatabase();
		List<CHObject> chObjects = decodeJson();
		
		System.out.println(chObjectRepository.findAll().size());
		System.out.println(imageRepository.findAll().size());
		System.out.println(participantRepository.findAll().size());
		System.out.println(roleRepository.findAll().size());
		System.out.println(participationRepository.findAll().size());
		
		

		
		
		for( CHObject aux : chObjects)
			chObjectService.save(aux);
		
		
		
		System.out.println(chObjectRepository.findAll().size());
		System.out.println(imageRepository.findAll().size());
		System.out.println(participantRepository.findAll().size());
		System.out.println(roleRepository.findAll().size());
		System.out.println(participationRepository.findAll().size());
		
		
		
	}
	
    public static void main(String[] args) {
        SpringApplication.run(JsonPopulation.class, args);
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
