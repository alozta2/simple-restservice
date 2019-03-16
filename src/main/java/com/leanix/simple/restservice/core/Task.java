package com.leanix.simple.restservice.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "task")
@NamedQueries(
    {
        @NamedQuery(
            name = "com.leanix.simple.restservice.core.Task.findAll",
            query = "SELECT t FROM Task t"
        ),
        @NamedQuery(
                name = "com.leanix.simple.restservice.core.Task.findByContent",
                query = "select t from Task t "
                        + "where t.name like :name "
                		+ "or t.description like :name"
            )
})
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "todo_id")
	@JsonIgnore						//to avoid stack overflow while serialization
	private Todo todo;

	public Task() {
	}

	public Task(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}
}
