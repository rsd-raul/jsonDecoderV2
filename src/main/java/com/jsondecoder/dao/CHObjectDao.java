package com.jsondecoder.dao;

import java.util.HashMap;

//import java.sql.ResultSet;
//import java.sql.SQLException;

//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
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
	
	
	
	
	
	
	
	/*	TODO aqui estamos, intentado recuperar una id con IdbcInsert
	 * 
	 * EL TRUCO ESTA EN QUE HACE LA CONSULTA CORRECTAMENTE, PERO AL NO PONERLE EL GENERATED CORRECTO
	 * NO ES CAPAZ DE RECUPERAR
	 * 
	 * TE DICEN QUE RECUPERES LA ID GENERADA PARA PARTICIPANTE... PUEDE QUE VALGA POR DEFECTO
	 * 
	 * */
	
	private SimpleJdbcInsert insertEmp;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.insertEmp = new SimpleJdbcInsert(dataSource).withTableName("CHObjects").usingGeneratedKeyColumns("id");
    }
	
	public int create(CHObject chObject) {  
        Map<String, Object> parameters = new HashMap<String, Object>();  

        parameters.put("id", chObject.getId());
        parameters.put("title", chObject.getTitle());
        parameters.put("dateObject", chObject.getDateObject());
        parameters.put("medium", chObject.getMedium());
        parameters.put("creditline", chObject.getCreditline());
        parameters.put("description", chObject.getDescription());
        parameters.put("gallery_text", chObject.getGallery_text());
        Number empid = insertEmp.executeAndReturnKey(parameters);  
        
        return empid.intValue();
    } 
}