package com.jsondecoder.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsondecoder.domain.Image;

public class ImageRowMapper implements RowMapper<Image> {

	@Override
	public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
		Image image = new Image();
		
		image.setId(rs.getInt("id"));
		image.setUrl(rs.getString("url"));
		image.setWidth(rs.getInt("width"));
		image.setHeight(rs.getInt("height"));
		image.setIs_primary(rs.getInt("is_primary"));

		return image;
	}

}
