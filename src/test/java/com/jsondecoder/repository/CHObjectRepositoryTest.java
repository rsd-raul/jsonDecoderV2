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
import com.jsondecoder.domain.CHObject;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DefaultConfig.class)
@ActiveProfiles("test")
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class CHObjectRepositoryTest {

    @Autowired
	CHObjectRepository chObjectRepository;
 
	@Test
    public void testFindById() {
    	CHObject chObject = chObjectRepository.findById(68268203);
  
    	Assert.assertNotNull(chObject);
    	Assert.assertEquals(68268203, chObject.getId().intValue());
    }

	@Test
	public void findAll() {
		List<CHObject> chObjects = chObjectRepository.findAll();
		
		assertEquals(35, chObjects.size());
	}
	
	@Test
	public void add() {		
		CHObject chObject = new CHObject();
		chObject.setCreditline("testing");
		chObject.setDescription("testing");
		chObject.setDateObject("testing");
		chObject.setGallery_text("testing");
		chObject.setId(123456789);
		chObject.setMedium("testing");
		chObject.setTitle("testing");
		
		chObjectRepository.add(chObject);
		
		List<CHObject> chObjects = chObjectRepository.findAll();
		assertEquals(36, chObjects.size());
	}
	
	@Test
	public void update() {		
		CHObject chObject = chObjectRepository.findById(68268203);
		chObject.setDescription("testing");
		chObjectRepository.update(chObject);
		
		chObject = chObjectRepository.findById(68268203);
		assertEquals("testing", chObject.getDescription());
	}
}