package com.jsondecoder.datasource;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


public class DataSourceUtil {

	@Bean
	public DataSource dataSource() {
		
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
			.setType(EmbeddedDatabaseType.H2)
			.addScript("db/sql/create-db.sql")
			.build();
		return db;
	}
}
