package com.jsondecoder.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsondecoder.domain.Participant;

public class ParticipantRowMapper implements RowMapper<Participant> {

	@Override
	public Participant mapRow(ResultSet rs, int rowNum) throws SQLException {
		Participant participant = new Participant();
		
					participant.setId(rs.getInt("id"));
					participant.setName(rs.getString("name"));
					participant.setBirth(rs.getString("birth"));
					participant.setUrl(rs.getString("url"));

		return participant;
	}

}
