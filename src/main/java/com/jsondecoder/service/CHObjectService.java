package com.jsondecoder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsondecoder.dao.CHObjectDao;

@Service
public class CHObjectService {
	
	@Autowired
	CHObjectDao chObjectDao;
	
	
	//TODO esto se ocupara de las operaciones complejas
/*
	for each CH Object, you need to do the following:
		•	Add the object
		•	Add the associated images, if any
		•	For each participation:
			o	Check if the participant exists
					If it does, you have a participant object with the id already set
					If it doesn’t, add the participant and retrieve the generated id (see SimpleJdbcInsert for how to do that – will be covered in class)
			o	Check if the role exists
					If it does, you have a participant object with the id already set
					If it doesn’t, add the participant and retrieve the generated id
			o	Add the participation row
*/
}
