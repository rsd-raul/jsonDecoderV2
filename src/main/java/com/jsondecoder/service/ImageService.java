package com.jsondecoder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsondecoder.repository.ImageRepository;
import com.jsondecoder.domain.CHObject;
import com.jsondecoder.domain.Image;

@Service
public class ImageService {
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	public ImageService(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}
	

	public void save(Image image, String size) {
		imageRepository.save(image, size);
	}
	
	public void saveRelation(Image image, CHObject chObject, String size) {
		imageRepository.addRelation(image, chObject, size);
	}
	
	public Image findById(int id, String size) {
		return imageRepository.findById(id, size);
	}

	public List<Image> findAll() {
		return imageRepository.findAll();
	}


}
