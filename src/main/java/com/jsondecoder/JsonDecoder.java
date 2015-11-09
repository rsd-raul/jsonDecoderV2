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

public class JsonDecoder {

	public static void main(String[] args){
		
		System.out.println("processing CHObject file " + args[0] +"\n");
		
		String chobjectsDirectory = args[0];
		
		List<Path> files;
		try {
			files = FileFinder.getFileList(chobjectsDirectory, "*.json");
			
			for (Path f : files) {
		        
		        File chobjectFile = new File(f.toString());
		        try {
					CHObject chobject = new ObjectMapper().readValue(chobjectFile, CHObject.class);
					System.out.println("\n Object being created: " + chobject.toString());
					
				} catch (JsonParseException e) {
					System.out.println("Error parsing the file.");
				} catch (JsonMappingException e) {
					System.out.println("Error mapping to Java object.");
				} catch (IOException e) {
					System.out.println("Unknown I/O error.");
				}
			}
			
		} catch (IOException e) {
			System.out.println("Unknown I/O error.");
		}
	}
}