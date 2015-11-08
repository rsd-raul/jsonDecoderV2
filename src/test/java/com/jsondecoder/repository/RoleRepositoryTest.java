package com.jsondecoder.repository;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.jsondecoder.DefaultConfig;
import com.jsondecoder.domain.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DefaultConfig.class)
@ActiveProfiles("test")
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class RoleRepositoryTest {

    @Autowired
	RoleRepository roleRepository;
 
	@Test
    public void testFindById() {
    	Role role = roleRepository.findById(35351535);
  
    	Assert.assertNotNull(role);
    	Assert.assertEquals(35351535, role.getId());
    }

	@Test
	public void findAll() {
		List<Role> roles = roleRepository.findAll();
		
		assertEquals(4, roles.size());
	}
	
	@Test
	public void add() {		
		Role role = new Role();
		role.setDisplay_name("testing");
		role.setId(1234);
		role.setName("testing");
		role.setUrl("testing");
		
		roleRepository.add(role);
		
		List<Role> roles = roleRepository.findAll();
		assertEquals(5, roles.size());
	}
	
	@Test
	public void update() {		
		Role role = roleRepository.findById(35351535);
		role.setDisplay_name("testing");
		roleRepository.update(role);
		
		role = roleRepository.findById(35351535);
		assertEquals("testing", role.getDisplay_name());
	}
}