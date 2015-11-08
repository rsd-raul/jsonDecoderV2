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
import com.jsondecoder.domain.Image;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DefaultConfig.class)
@ActiveProfiles("test")
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class ImageRepositoryTest {

    @Autowired
	ImageRepository imageRepository;
    @Autowired
	CHObjectRepository chObjectRepository;
    
	@Test
    public void testFindById() {
    	Image image = imageRepository.findById(90403, "b");
  
    	Assert.assertNotNull(image);
    	Assert.assertEquals(90403, image.getId());
    }

	@Test
	public void findAll() {
		List<Image> images = imageRepository.findAll();
		
		assertEquals(200, images.size());
	}
	
	@Test
	public void add() {		
		Image image = new Image();
		image.setHeight(1234);
		image.setId(1234);
		image.setIs_primary(1);
		image.setUrl("testing");
		image.setWidth(1234);
		
		imageRepository.add(image, "b");
		
		List<Image> images = imageRepository.findAll();
		assertEquals(201, images.size());
	}
	
	@Test
	public void addRelation() {		
		Image image = imageRepository.findById(90403, "b");
		CHObject chObject = chObjectRepository.findById(68268203);
		
		imageRepository.addRelation(image, chObject, "b");
	}
	
	@Test
	public void update() {		
		Image image = imageRepository.findById(90403, "b");
		image.setHeight(333);
		imageRepository.update(image);
		
		image = imageRepository.findById(90403, "b");
		assertEquals(333, image.getHeight());
	}
	
}