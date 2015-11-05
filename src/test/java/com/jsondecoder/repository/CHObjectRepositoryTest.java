package com.jsondecoder.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.jsondecoder.domain.CHObject;

public class CHObjectRepositoryTest {

    private EmbeddedDatabase db;

    CHObjectRepository cHObjectRepository;
    
    @Before
    public void setUp() {
        //db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
    	db = new EmbeddedDatabaseBuilder()
    		.setType(EmbeddedDatabaseType.H2)
    		.addScript("db/sql/create-db.sql")
    		.addScript("db/sql/insert-data.sql")
    		.build();
    }

    @Test
    public void testFindByname() {
    	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
    	CHObjectRepository cHObjectRepository = new CHObjectRepository();
    	cHObjectRepository.setNamedParameterJdbcTemplate(template);
    	
    	CHObject cHObject = cHObjectRepository.findByName("Folder, Trees, 1960");
  
    	Assert.assertNotNull(cHObject);
    	Assert.assertEquals(68268203, cHObject.getId().intValue());
    	System.out.println(cHObject);
//    	Assert.assertEquals("mkyong@gmail.com", cHObject.getEmail());

    }

    @After
    public void tearDown() {
        db.shutdown();
    }

}