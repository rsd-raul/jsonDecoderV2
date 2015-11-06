package com.jsondecoder.repository;

//import java.sql.ResultSet;
//import java.sql.SQLException;

//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;

//import com.fasterxml.jackson.annotation.JsonProperty;
import com.jsondecoder.domain.CHObject;
import com.jsondecoder.rowmapper.CHObjectRowMapper;



@Component
public class CHObjectDao {

	
	
//	public void insertCHObject(CHObject chObject) {
//		
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("id", chObject.getId());
//		params.put("title", chObject.getTitle());
//		params.put("dateObject", chObject.getDateObject());
//		params.put("medium", chObject.getMedium());
//		params.put("creditline", chObject.getCreditline());
//		params.put("description", chObject.getDescription());
//		params.put("gallery_text", chObject.getGallery_text());
//        
////		String sql = "SELECT * FROM CHObjects WHERE title=:title";
//		String sql = "INSERT INTO CHObjects VALUES (:id, :title, :dateObject, :medium, :creditline, :description, :gallery_text)"; 
//		
//        CHObject result = namedParameterJdbcTemplate.queryForObject(
//                    sql,
//                    params,
//                    new CHObjectRowMapper());
//                    
//        //new BeanPropertyRowMapper(Customer.class));
//        
//        return result;
//        
//	}
	
//	public void save(CHObject chObject) {
//		if (chObject.getId() != 0) {
//			add(chObject);
//		} else {
//			add(chObject);
//		}
//	}
//	
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public CHObjectDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public CHObject getById(int id) {
		String sql = "SELECT * FROM CHObjects WHERE id = ?";
		CHObject chObject = jdbcTemplate.queryForObject(sql, new Object[] { id }, new CHObjectRowMapper());
		return chObject;
	}
	
	public void save(CHObject chObject) {
		if (chObject.getId() != 0) {
			update(chObject);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//	
//	@Autowired
//	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//	}
//		
//	public CHObject findByName(String title) {
//		
//		Map<String, Object> params = new HashMap<String, Object>();
//        params.put("title", title);
//        
//		String sql = "SELECT * FROM cHObjects WHERE title=:title";
//		
//        CHObject result = namedParameterJdbcTemplate.queryForObject(
//                    sql,
//                    params,
//                    new CHObjectRowMapper());
//                    
//        //new BeanPropertyRowMapper(Customer.class));
//        
//        return result;
//        
//	}
//
//	public List<CHObject> findAll() {
//		
//		Map<String, Object> params = new HashMap<String, Object>();
//		
//		String sql = "SELECT * FROM cHObjects";
//		
//        List<CHObject> result = namedParameterJdbcTemplate.query(sql, params, new CHObjectRowMapper());
//        
//        return result;
//        
//	}
}