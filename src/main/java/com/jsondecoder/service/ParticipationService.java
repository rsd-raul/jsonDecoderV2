package com.jsondecoder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsondecoder.repository.ParticipationRepository;
import com.jsondecoder.domain.CHObject;
import com.jsondecoder.domain.Participation;

@Service
public class ParticipationService {
	
	@Autowired
	ParticipationRepository participationRepository;
	
	@Autowired
	public ParticipationService(ParticipationRepository participationRepository) {
		this.participationRepository = participationRepository;
	}
	
	public void save(Participation participation, CHObject chObject) {
		participationRepository.save(participation, chObject);
	}

	public Participation findById(int chObject_id, int participant_id, int role_id) {
		return participationRepository.findById(chObject_id, participant_id, role_id);
	}

	public List<Participation> findAll() {
		return participationRepository.findAll();
	}
}
