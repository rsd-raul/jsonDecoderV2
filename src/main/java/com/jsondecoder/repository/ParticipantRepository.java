package com.jsondecoder.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.jsondecoder.domain.Participant;
import com.jsondecoder.rowmapper.ParticipantRowMapper;

@Component
public class ParticipantRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ParticipantRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Participant findById(int id) {
		String sql = "SELECT * FROM Participants WHERE id = ?";
		Participant participant = jdbcTemplate.queryForObject(sql, new Object[] { id }, new ParticipantRowMapper());
		
		return participant;
	}
	
	public void save(Participant participant) {
		if (participant.getId() != 0) {
//			update(participant);
			add(participant);
		} else {
			add(participant);
		}
	}
	
	public void add(Participant participant) {
		String sql = "INSERT INTO Participants (id, name, birth, url) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] { participant.getId(), 
												participant.getName(),
												participant.getBirth(),
												participant.getUrl()} );
		

		  
	}
		
	public void update(Participant participant) {
		String sql = "UPDATE Participants SET name = ?, birth = ?, url = ? WHERE id = ?";
		jdbcTemplate.update(sql, new Object[] {	participant.getName(),
												participant.getBirth(),
												participant.getUrl(),
												
												participant.getId()} );
	}
	
	public List<Participant> findAll() {
		String sql = "SELECT * FROM Participants";
		return jdbcTemplate.query(sql, new Object[] { }, new ParticipantRowMapper());
	}

}