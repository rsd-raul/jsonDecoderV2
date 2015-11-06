package com.jsondecoder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsondecoder.repository.ParticipantRepository;
import com.jsondecoder.domain.Participant;

@Service
public class ParticipantService {
	
	@Autowired
	ParticipantRepository participantRepository;
	
	@Autowired
	public ParticipantService(ParticipantRepository participantRepository) {
		this.participantRepository = participantRepository;
	}
	

	public void save(Participant participant) {
		participantRepository.save(participant);
	}

	public Participant findById(int id) {
		return participantRepository.findById(id);
	}

	public List<Participant> findAll() {
		return participantRepository.findAll();
	}

}
