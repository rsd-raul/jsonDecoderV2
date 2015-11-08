package com.jsondecoder.service;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.jsondecoder.domain.Role;
import com.jsondecoder.repository.RoleRepository;

public class RoleServiceTest {

	private RoleService roleService;
	private RoleRepository repoMock;
	
	@Before
	public void setup() {
		repoMock = mock(RoleRepository.class);
		
		Role a = new Role();
		a.setName("John");
		a.setId(1);
		a.setGender("male");
		
		Role b = new Role();
		b.setName("Mary");
		b.setId(2);
		b.setGender("female");
		
		List<Role> roles = new ArrayList<>();
		
		roles.add(a);
		roles.add(b);
		
		when(repoMock.get(1)).thenReturn(a);
		when(repoMock.get(2)).thenReturn(b);
	
		when(repoMock.findAll()).thenReturn(roles);
		
		roleService = new RoleService(repoMock);
	}
	
	@Test
	public void get() {
		assertTrue(roleService.get(1).getName().equals("John"));
	}
	
	@Test
	public void findAll() {
		assertTrue(roleService.findAll().size() == 2);
	}
	
}
