package com.jsondecoder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsondecoder.repository.ImageRepository;
import com.jsondecoder.domain.Image;

@Service
public class ImageService {
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	public ImageService(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}
	

	public void save(Image image) {
		imageRepository.save(image);
	}

	public Image findById(int id) {
		return imageRepository.findById(id);
	}

	public List<Image> findAll() {
		return imageRepository.findAll();
	}
}
