package com.tankdev.hikari.config;

import com.zaxxer.hikari.HikariDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author javadev.top
 *
 * @since 2020-04-24 03:37
 */
@Configuration
public class DataSourceConfig {
	@Bean
	@Primary
	@ConfigurationProperties("app.datasource.first")
	public DataSourceProperties firstDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	@ConfigurationProperties("app.datasource.first.configuration")
	public HikariDataSource firstDataSource() {
		return firstDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean
	@ConfigurationProperties("app.datasource.second")
	public DataSourceProperties secondDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties("app.datasource.second.configuration")
	public HikariDataSource secondDataSource() {
		return secondDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	@Bean
	public BeetlSqlDataSource beetlSqlDataSource(@Qualifier("firstDataSource") DataSource firstDataSource,
			@Qualifier("secondDataSource") DataSource secondDataSource) {
		BeetlSqlDataSource source = new BeetlSqlDataSource();
		source.setMasterSource(firstDataSource);
		source.setSlaves(new DataSource[]{secondDataSource});
		return source;
	}
}
