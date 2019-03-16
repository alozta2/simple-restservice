package com.leanix.simple.restservice.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskRepr {

	private int id;
	private String name;
	private String description;
	
	
	public TaskRepr() {
		// Jackson deserialization
	}
	
	public TaskRepr(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public TaskRepr(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

    @JsonProperty
	public long getId() {
		return id;
	}
    
	public void setId(int id) {
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
