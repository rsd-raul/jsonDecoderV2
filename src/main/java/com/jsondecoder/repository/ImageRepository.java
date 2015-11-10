package com.jsondecoder.repository;

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
	
	public Image findById(int id, String size) {
		String sql = "SELECT * FROM Images WHERE id = ? and size = ?";
		Image image = jdbcTemplate.queryForObject(sql, new Object[] { id, size }, new ImageRowMapper());
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
}