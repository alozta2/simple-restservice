package com.leanix.simple.restservice.representation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoRepr {

	private int id;
	private String name;
	private String description;
	private List<TaskRepr> tasks;
	
	
	public TodoRepr() {
	}
	
	public TodoRepr(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public TodoRepr(int id, String name, String description, List<TaskRepr> tasks) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.tasks = tasks;
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

    @JsonProperty
	public List<TaskRepr> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<TaskRepr> tasks) {
		this.tasks = tasks;
	}
}
