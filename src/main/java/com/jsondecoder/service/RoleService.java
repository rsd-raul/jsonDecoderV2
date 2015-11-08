package com.jsondecoder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsondecoder.repository.RoleRepository;
import com.jsondecoder.domain.Role;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public void update(Role role) {
		roleRepository.update(role);
	}
	
	public void save(Role role) {
		roleRepository.save(role);
	}

	public Role findById(int id) {
		return roleRepository.findById(id);
	}

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

}
