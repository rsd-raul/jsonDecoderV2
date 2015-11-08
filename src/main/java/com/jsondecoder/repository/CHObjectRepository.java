package com.jsondecoder.repository;

//import java.sql.ResultSet;
//import java.sql.SQLException;

//import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;

//import com.fasterxml.jackson.annotation.JsonProperty;
import com.jsondecoder.domain.CHObject;
import com.jsondecoder.rowmapper.CHObjectRowMapper;

@Component
public class CHObjectRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public CHObjectRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public CHObject findById(int id) {
		String sql = "SELECT * FROM CHObjects WHERE id = ?";
		CHObject chObject = jdbcTemplate.queryForObject(sql, new Object[] { id }, new CHObjectRowMapper());
		return chObject;
	}
	
	public void save(CHObject chObject) {
		if (chObject.getId() != 0) {
//			update(chObject);
			add(chObject);
		} else {
			add(chObject);
		}
	}
	
	public void add(CHObject chObject) {
		String sql = "INSERT INTO CHObjects (id, title, dateObject, medium, creditline, description, gallery_text) VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] { chObject.getId(), 
												chObject.getTitle(),
												chObject.getDateObject(),
												chObject.getMedium(),
												chObject.getCreditline(),
												chObject.getDescription(),
												chObject.getGallery_text()} );
	}
		
	public void update(CHObject chObject) {
		String sql = "UPDATE CHObjects SET title = ?, dateObject = ?, medium = ?, creditline = ?, description = ?, gallery_text = ? WHERE id = ?";
		jdbcTemplate.update(sql, new Object[] { chObject.getTitle(),
												chObject.getDateObject(),
												chObject.getMedium(),
												chObject.getCreditline(),
												chObject.getDescription(),
												chObject.getGallery_text(),
												
												chObject.getId()} );
	}
	
	public List<CHObject> findAll() {
		String sql = "SELECT * FROM CHObjects";
		return jdbcTemplate.query(sql, new Object[] { }, new CHObjectRowMapper());
	}
}