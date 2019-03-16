package com.leanix.simple.restservice.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskRepr {

	private long id;
	private String name;
	private String description;
	
	
	public TaskRepr() {
	}
	
	public TaskRepr(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public TaskRepr(long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

    @JsonProperty
	public long getId() {
		return id;
	}
    
	public void setId(long id) {
		this.id = id;
	}

    @JsonProperty
	public String getName() {
		return name;
	}
    
	public void setName(String name) {
		this.name = name;
	}

    @JsonProperty
	public String getDescription() {
		return description;
	}
    
	public void setDescription(String description) {
		this.description = description;
	}
}
