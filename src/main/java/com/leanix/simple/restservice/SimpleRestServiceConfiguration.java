package com.leanix.simple.restservice;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class SimpleRestServiceConfiguration extends Configuration {
	
	@NotEmpty
	private String content;

	@NotEmpty
	private String defaultMessage = "test";
	
	/**
     * A factory used to connect to a relational database management system.
     * Factories are used by Dropwizard to group together related configuration parameters such as database connection 
     * driver, URI, password etc.
     */
	@Valid
    @NotNull
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

	
    @JsonProperty
	public String getContent() {
		return content;
	}

    @JsonProperty
	public void setContent(String content) {
		this.content = content;
	}

    @JsonProperty
	public String getDefaultMessage() {
		return defaultMessage;
	}

    @JsonProperty
	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

    /**
     * @return An instance of database factory deserialized from the
     * configuration file passed as a command-line argument to the application.
     */
    @JsonProperty("database")
	public DataSourceFactory getDataSourceFactory() {
		return dataSourceFactory;
	}

    @JsonProperty("database")
	public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
		this.dataSourceFactory = dataSourceFactory;
	}
}
