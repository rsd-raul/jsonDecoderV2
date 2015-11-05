package com.jsondecoder.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.jsondecoder.domain.CHObject;

@Repository
public class CHObjectRepository {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	public CHObject findByName(String title) {
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("title", title);
        
		String sql = "SELECT * FROM cHObjects WHERE title=:title";
		
        CHObject result = namedParameterJdbcTemplate.queryForObject(
                    sql,
                    params,
                    new CHObjectMapper());
                    
        //new BeanPropertyRowMapper(Customer.class));
        
        return result;
        
	}


	public List<CHObject> findAll() {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		String sql = "SELECT * FROM cHObjects";
		
        List<CHObject> result = namedParameterJdbcTemplate.query(sql, params, new CHObjectMapper());
        
        return result;
        
	}

	private static final class CHObjectMapper implements RowMapper<CHObject> {

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
}
