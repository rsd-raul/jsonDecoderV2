package com.jsondecoder.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.jsondecoder.domain.Participation;
import com.jsondecoder.repository.ParticipantRepository;
import com.jsondecoder.repository.RoleRepository;
import com.jsondecoder.service.ParticipantService;
import com.jsondecoder.service.RoleService;


public class ParticipationRowMapper implements RowMapper<Participation> {

	@Autowired
	ParticipantService participantService;
	@Autowired
	RoleService roleService;
	
	public ParticipationRowMapper(ParticipantService participantService, RoleService roleService) {
		this.participantService = participantService;
		this.roleService = roleService;
	}

	@Override
	public Participation mapRow(ResultSet rs, int rowNum) throws SQLException {
		Participation participation = new Participation();
		
		participation.setParticipant(participantService.findById(rs.getInt("participant_id")));
		participation.setRole(roleService.findById(rs.getInt("role_id")));

		return participation;
	}

}
