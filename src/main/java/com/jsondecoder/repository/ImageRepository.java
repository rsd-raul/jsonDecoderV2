package com.jsondecoder.repository;

//import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jsondecoder.domain.CHObject;
import com.jsondecoder.domain.Image;
import com.jsondecoder.rowmapper.ImageRowMapper;

@Component
public class ImageRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ImageRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Image findById(int id) {
		String sql = "SELECT * FROM Images WHERE id = ?";
		Image image = jdbcTemplate.queryForObject(sql, new Object[] { id }, new ImageRowMapper());
		return image;
	}
	
	public void save(Image image, String size) {
		if (image.getId() != 0) {
//			update(image);
			add(image, size);
		} else {
			add(image, size);
		}
	}
	
	public void add(Image image, String size) {
		String sql = "INSERT INTO Images (id, url, width, height, is_primary, size) VALUES (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] { image.getId(), 
												image.getUrl(),
												image.getWidth(),
												image.getHeight(),
												image.getIs_primary(),
												size} );
	}
	
	public void addRelation(Image image, CHObject chObject, String size) {
		String sql = "INSERT INTO CHObjects_Images (chObject_id, image_id, size) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] { chObject.getId(), 
												image.getId(),
												size} );
	}
		
	public void update(Image image) {
		String sql = "UPDATE Images SET url = ?, width = ?, height = ?, is_primary = ? WHERE id = ?";
		jdbcTemplate.update(sql, new Object[] { image.getUrl(),
												image.getWidth(),
												image.getHeight(),
												image.getIs_primary(),
												
												image.getId()} );
	}
	
	public List<Image> findAll() {
		String sql = "SELECT * FROM Images";
		return jdbcTemplate.query(sql, new Object[] { }, new ImageRowMapper());
	}
	

	
	
	/*	TODO aqui estamos, intentado recuperar una id con IdbcInsert
	 * 
	 * EL TRUCO ESTA EN QUE HACE LA CONSULTA CORRECTAMENTE, PERO AL NO PONERLE EL GENERATED CORRECTO
	 * NO ES CAPAZ DE RECUPERAR
	 * 
	 * TE DICEN QUE RECUPERES LA ID GENERADA PARA PARTICIPANTE... PUEDE QUE VALGA POR DEFECTO
	 * 
	 * */
	
//	private SimpleJdbcInsert insertEmp;
//	
//	@Autowired
//    public void setDataSource(DataSource dataSource) {
//        this.insertEmp = new SimpleJdbcInsert(dataSource).withTableName("Images").usingGeneratedKeyColumns("id");
//    }
//	
//	public int create(Image image) {  
//        Map<String, Object> parameters = new HashMap<String, Object>();  
//
//        parameters.put("id", image.getId());
//        parameters.put("title", image.getTitle());
//        parameters.put("dateObject", image.getDateObject());
//        parameters.put("medium", image.getMedium());
//        parameters.put("creditline", image.getCreditline());
//        parameters.put("description", image.getDescription());
//        parameters.put("gallery_text", image.getGallery_text());
//        Number empid = insertEmp.executeAndReturnKey(parameters);  
//        
//        return empid.intValue();
//    } 
}