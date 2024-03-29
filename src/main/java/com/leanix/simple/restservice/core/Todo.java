package com.leanix.simple.restservice.core;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "todo")
@NamedQueries(
    {
    	@NamedQuery(
                name = "com.leanix.simple.restservice.core.Todo.findAll",
                query = "SELECT t FROM Todo t "
                		+ "WHERE status != 7"	//status 7 represents dummy todo, deleted tasks will be assign to this todo
        ),
        @NamedQuery(
            name = "com.leanix.simple.restservice.core.Todo.findAllByStatus",
            query = "SELECT t FROM Todo t "
            		+ "WHERE t.status = :status"
        ),
        @NamedQuery(
                name = "com.leanix.simple.restservice.core.Todo.findByContent",
                query = "SELECT t FROM Todo t "
                        + "WHERE t.name like :name "
                		+ "or t.description like :name"
            )
})
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
	@OneToMany(targetEntity = Task.class, mappedBy = "todo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Task> tasks;
	
	/**
	 * Status: 0 not-completed, 1 completed, 2 deleted ...
	 * */
	@Column(name = "status")
	private byte status;

	public Todo() {
	}

	/**
     * A constructor to create todos. Id is not passed, cause it's auto-generated by RDBMS.
     *
     * @param name todo name
     * @param description todo description
     */
	public Todo(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	/**
	 * Setting Task.todo to this class to persist the relation between them.
	 * */
	public Todo registerTaskTodoRelation() {
		for(Task t : this.getTasks()) {
			t.setTodo(this);
		}
		
		return this;
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

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
}
