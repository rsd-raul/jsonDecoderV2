package com.jsondecoder.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsondecoder.domain.CHObject;

public class CHObjectRowMapper implements RowMapper<CHObject> {

	@Override
	public CHObject mapRow(ResultSet rs, int rowNum) throws SQLException {
		CHObject cHObject = new CHObject();
		
		cHObject.setId(rs.getInt("id"));
		cHObject.setTitle(rs.getString("title"));
		cHObject.setDateObject(rs.getString("dateObject"));
		cHObject.setMedium(rs.getString("medium"));
		cHObject.setCreditline(rs.getString("creditline"));
		cHObject.setDescription(rs.getString("description"));
		cHObject.setGallery_text(rs.getString("gallery_text"));

		return cHObject;
	}

}
