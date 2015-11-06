package com.jsondecoder.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.jsondecoder.domain.Role;
import com.jsondecoder.rowmapper.RoleRowMapper;

@Component
public class RoleRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public RoleRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Role findById(int id) {
		String sql = "SELECT * FROM Roles WHERE id = ?";
		Role role = jdbcTemplate.queryForObject(sql, new Object[] { id }, new RoleRowMapper());
		return role;
	}
	
	public void save(Role role) {
		if (role.getId() != 0) {
//			update(role);
			add(role);
		} else {
			add(role);
		}
	}
	
	public void add(Role role) {
		String sql = "INSERT INTO Roles (id, name, display_name, url) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] { role.getId(), 
												role.getName(),
												role.getDisplay_name(),
												role.getUrl()} );
	}
		
	public void update(Role role) {
		String sql = "UPDATE Roles SET name = ?, display_name = ?, url = ? WHERE id = ?";
		jdbcTemplate.update(sql, new Object[] {	role.getName(),
												role.getDisplay_name(),
												role.getUrl(),
												
												role.getId()} );
	}
	
	public List<Role> findAll() {
		String sql = "SELECT * FROM Roles";
		return jdbcTemplate.query(sql, new Object[] { }, new RoleRowMapper());
	}
}