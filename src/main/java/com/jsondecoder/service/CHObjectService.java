package com.jsondecoder.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsondecoder.repository.CHObjectRepository;
import com.jsondecoder.domain.CHObject;
import com.jsondecoder.domain.Image;

@Service
public class CHObjectService {
	
	@Autowired
	CHObjectRepository chObjectRepository;
	
	@Autowired
	public CHObjectService(CHObjectRepository chObjectRepository) {
		this.chObjectRepository = chObjectRepository;
	}
	

	public void save(CHObject chObject) {
		
//		•	Add the object
		chObjectRepository.save(chObject);
		
//		•	Add the associated images, if any
		System.out.println("EL TAMAÑO DE LA LISTA ES: " + chObject.getImages().size());
		for (Map<String, Image> imageMultipleSizes : chObject.getImages()) {
			for(String size : imageMultipleSizes.keySet()){
				Image imageWithSize = imageMultipleSizes.get(size);
				
				
				//TODO AQUI ESTAMOS
			}	
			System.out.println("UN SOLO MAPA TIENE: " + imageMultipleSizes.toString());
		}
		
//		•	For each participation:
//			o	Check if the participant exists
//					If it does, you have a participant object with the id already set
//					If it doesn’t, add the participant and retrieve the generated id (see SimpleJdbcInsert for how to do that – will be covered in class)
//			o	Check if the role exists
//					If it does, you have a participant object with the id already set
//					If it doesn’t, add the participant and retrieve the generated id
//			o	Add the participation row
	}

	public CHObject findById(int id) {
		return chObjectRepository.findById(id);
	}

	public List<CHObject> findAll() {
		return chObjectRepository.findAll();
	}
	

	
	
	
	//TODO esto se ocupara de las operaciones complejas
/*
	for each CH Object, you need to do the following:
		•	Add the object
		•	Add the associated images, if any
		•	For each participation:
			o	Check if the participant exists
					If it does, you have a participant object with the id already set
					If it doesn’t, add the participant and retrieve the generated id (see SimpleJdbcInsert for how to do that – will be covered in class)
			o	Check if the role exists
					If it does, you have a participant object with the id already set
					If it doesn’t, add the participant and retrieve the generated id
			o	Add the participation row
*/
}
