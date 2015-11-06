package com.jsondecoder.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.jsondecoder.domain.CHObject;
import com.jsondecoder.domain.Participation;
import com.jsondecoder.rowmapper.ParticipationRowMapper;

@Component
public class ParticipationRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ParticipationRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Participation findById(int chObject_id, int participant_id, int role_id) {
		String sql = "SELECT * FROM CHObjects_Participants_Roles WHERE chObject_id = ? AND participant_id = ? AND role_id = ? ";
		Participation participation = jdbcTemplate.queryForObject(sql, new Object[] { chObject_id, participant_id, role_id }, new ParticipationRowMapper());
		return participation;
	}
	
	public void save(Participation participation, CHObject chObject) {
		add(participation, chObject);
	}
	
	public void add(Participation participation, CHObject chObject) {
		String sql = "INSERT INTO CHObjects_Participants_Roles (chObject_id, participant_id, role_id) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] { chObject.getId(),
												participation.getParticipant().getId(), 
												participation.getRole()} );
	}
		
//	public void update(Participation participation) {
//		String sql = "UPDATE CHObjects_Participants_Roles SET title = ?, dateObject = ?, medium = ?, creditline = ?, description = ?, gallery_text = ? WHERE id = ?";
//		jdbcTemplate.update(sql, new Object[] { participation.getTitle(),
//												participation.getDateObject(),
//												participation.getMedium(),
//												participation.getCreditline(),
//												participation.getDescription(),
//												participation.getGallery_text(),
//												
//												participation.getId()} );
//	}
	
	public List<Participation> findAll() {
		String sql = "SELECT * FROM CHObjects_Participants_Roles";
		return jdbcTemplate.query(sql, new Object[] { }, new ParticipationRowMapper());
	} 
}