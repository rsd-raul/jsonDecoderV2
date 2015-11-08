package com.jsondecoder.service;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import com.jsondecoder.domain.Role;
import com.jsondecoder.repository.RoleRepository;

public class RoleServiceTest {

	private RoleService roleService;
	private RoleRepository repoMock;
	
	@Before
	public void setup() {
		repoMock = mock(RoleRepository.class);
		
		Role a = new Role();
		a.setDisplay_name("testing DisplayName 1");
		a.setId(1);
		a.setName("testing Name 1");
		a.setUrl("testing Url 1");
		
		Role b = new Role();
		b.setDisplay_name("testing DisplayName 2");
		b.setId(2);
		b.setName("testing Name 2");
		b.setUrl("testing Url 2");
		
		List<Role> roles = new ArrayList<Role>();
		
		roles.add(a);
		roles.add(b);
		
		when(repoMock.findById(1)).thenReturn(a);
		when(repoMock.findById(2)).thenReturn(b);
		when(repoMock.findAll()).thenReturn(roles);
		
		roleService = new RoleService(repoMock);
	}
	
	@Test
    public void testFindById() {
    	Role role = roleService.findById(1);
  
    	Assert.assertNotNull(role);
    	Assert.assertEquals("testing Name 1", role.getName());
    }
	
	@Test
	public void findAll() {
		List<Role> roles = roleService.findAll();
		assertEquals(2, roles.size());
	}
	
	@Test
	public void add() {		
		Role role = new Role();
		role.setDisplay_name("testing 3");
		role.setId(1234);
		role.setName("testing 3");
		role.setUrl("testing 3");
		
		roleService.save(role);
		
		List<Role> roles = roleService.findAll();
		assertEquals(3, roles.size());
	}
	
	@Test
	public void update() {		
		Role role = roleService.findById(2);
		role.setDisplay_name("testing");
		roleService.update(role);
		
		role = roleService.findById(2);
		assertEquals("testing", role.getDisplay_name());
	}
	
}
