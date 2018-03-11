package com.blue.wappsender.conf;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion de persistencia
 * @author jmendoza
 *
 */
@Configuration
public class DataSourceConfig {
	
	
	@Bean(name="dataSource")
	public DataSource getDataSource() {
		DataSource dataSource = DataSourceBuilder
				.create()
				.url("jdbc:mysql://tviw6wn55xwxejwj.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/r64ltc8bpwfhx9vz")
				.username("cdalecwem3nxij68")
				.password("lajfbi7nynpakgfi")
				.driverClassName("com.mysql.jdbc.Driver")
				.build();
		//TODO: Buscar una manera mejor de confiruar la cantida maxima de conexiones
		org.apache.tomcat.jdbc.pool.DataSource ds = (org.apache.tomcat.jdbc.pool.DataSource) dataSource;
		ds.setMaxActive(5);
		return ds;
	}
	
	
	/*
	@Bean(name="dataSourceLocal")
	public DataSource getLocalDataSource() {
		return DataSourceBuilder
				.create()
				.url("jdbc:mysql://localhost:3306/blue_wapp")
				.username("root")
				.password("root")
				.driverClassName("com.mysql.jdbc.Driver")
				.build();
	}
	*/


}
