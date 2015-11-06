package com.jsondecoder.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.jsondecoder.domain.Participation;
import com.jsondecoder.repository.ParticipantRepository;
import com.jsondecoder.repository.RoleRepository;

public class ParticipationRowMapper implements RowMapper<Participation> {

	@Autowired
	ParticipantRepository participantRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Participation mapRow(ResultSet rs, int rowNum) throws SQLException {
		Participation participation = new Participation();
		
		participation.setParticipant(participantRepository.findById(rs.getInt("participant_id")));
		participation.setRole(roleRepository.findById(rs.getInt("role_id")));

		return participation;
	}

}
