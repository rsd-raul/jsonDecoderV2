package com.jsondecoder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsondecoder.domain.CHObject;
import com.jsondecoder.utilities.FileFinder;

public class Application {

	public static void main(String[] args){
		
//		String chobjectFile = args[0];
		System.out.println("processing CHObject file " + args[0] +"\n");
		
		String chobjectsDirectory = args[0];
		
		List<Path> files;
		try {
			files = FileFinder.getFileList(chobjectsDirectory, "*.json");
			
			for (Path f : files) {
//				Artist artist = new ObjectMapper().readValue(f.toFile(), Artist.class);
//				Long artistNode = addArtistNode(artist);
//		        connectArtistToMovements(artistNode, artist.getMovements());
//		        connectArtistToBirthPlace(artistNode, artist.getBirth());
		        
		        File chobjectFile = new File(f.toString());
		        try {
					CHObject chobject = new ObjectMapper().readValue(chobjectFile, CHObject. class);
					System.out.println("\n" + chobject.toString());
					
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
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}