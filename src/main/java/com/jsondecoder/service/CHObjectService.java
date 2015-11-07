package com.jsondecoder.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsondecoder.domain.CHObject;
import com.jsondecoder.domain.Image;
import com.jsondecoder.domain.Participant;
import com.jsondecoder.domain.Participation;
import com.jsondecoder.domain.Role;
import com.jsondecoder.repository.CHObjectRepository;

@Service
public class CHObjectService {
	
	@Autowired
	CHObjectRepository chObjectRepository;
	@Autowired
	ImageService imageService;
	@Autowired
	ParticipantService participantService;
	@Autowired
	ParticipationService participationService;
	@Autowired
	RoleService roleService;
	
	@Autowired
	public CHObjectService(CHObjectRepository chObjectRepository) {
		this.chObjectRepository = chObjectRepository;
	}
	

	public void save(CHObject chObject) {
		
//		•	Add the object
		chObjectRepository.save(chObject);
		
//		•	Add the associated images, if any
		for (Map<String, Image> multipleSizeImages : chObject.getImages()) 
			for(String size : multipleSizeImages.keySet()){
				Image imageForSize = multipleSizeImages.get(size);
			
				imageService.save(imageForSize, size);
				imageService.saveRelation(imageForSize, chObject, size);
			}
//		•	For each participation:
		for (Participation participation : chObject.getParticipants()) {
					
//			o	Check if the participant exists
//					If it does, you have a participant object with the id already set
			Participant participant = participation.getParticipant();
			try {
				participantService.findById(participant.getId());
			} catch (Exception e) {
//					If it doesn’t, add the participant and retrieve the generated id
				participantService.save(participant);
			}
			
//			o	Check if the role exists
//					If it does, you have a role object with the id already set
			Role role = participation.getRole();
			try {
				roleService.findById(role.getId());
			} catch (Exception e) {
//					If it doesn’t, add the role and retrieve the generated id
				roleService.save(role);
			}
			
//			o	Add the participation row
			participationService.save(participation, chObject);
		}
	}

	public CHObject findById(int id) {
		return chObjectRepository.findById(id);
	}

	public List<CHObject> findAll() {
		return chObjectRepository.findAll();
	}
}
