package com.jsondecoder.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsondecoder.domain.Role;

public class RoleRowMapper implements RowMapper<Role> {

	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		Role role = new Role();
		
		role.setId(rs.getInt("id"));
		role.setName(rs.getString("name"));
		role.setDisplay_name(rs.getString("display_name"));
		role.setUrl(rs.getString("url"));

		return role;
	}
}
