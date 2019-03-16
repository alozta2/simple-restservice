package com.leanix.simple.restservice.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import com.leanix.simple.restservice.core.Todo;

import io.dropwizard.hibernate.AbstractDAO;

public class TodoDao extends AbstractDAO<Todo> {

	/**
     * @param sessionFactory Hibernate session factory.
     */
	public TodoDao(SessionFactory factory) {
		super(factory);
	}

	@SuppressWarnings("unchecked")
	public List<Todo> findAll() {
		return list(namedQuery("com.leanix.simple.restservice.core.Todo.findAll"));
	}
	
	/**
     * @param name query parameter
     * @return list of todos whose content contains the passed parameter as a substring.
     */
	@SuppressWarnings("unchecked")
	public List<Todo> findByName(String name) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(name).append("%");
        return list(namedQuery("com.leanix.simple.restservice.core.Todo.findByContent")
                	.setParameter("name", builder.toString())
        );
	}

	/**
     * @param id the id of an todo
     * @return Optional containing the found todo or an empty Optional otherwise.
     */
	public Optional<Todo> findById(int id) {
		return Optional.ofNullable(get(id));
	}

//	public Todo create(Todo todo) {
//		return persist(todo);
//	}

	// @SqlUpdate("INSERT INTO todo (todo_id,name,description) VALUES(:name,
	// :phoneNumber, :acronym)")
	// @GetGeneratedKeys
	// int addTodo(@BindBean TodoRepr employee);

	// @SqlQuery("SELECT * FROM todo")
	// List<TodoRepr> getAll();

	// @SqlUpdate("DELETE FROM todo WHERE todo_id = :id")
	// int removeTodoById(@Bind("id") int id);

	// Initialize database
	// @SqlUpdate("CREATE TABLE todo (todo_id INT NOT NULL AUTO_INCREMENT PRIMARY
	// KEY,name VARCHAR(50),description VARCHAR(50))")
	// void createTodoTable();
	//
	// @SqlUpdate("INSERT INTO todo (name, description) VALUES ('first todo', 'this
	// the first todo.')")
	// int addTodo1();
	//
	// @SqlUpdate("INSERT INTO todo (name) VALUES ('second todo')")
	// int addTodo2();

	// @SqlUpdate("CREATE TABLE IF NOT EXISTS task (task_id INT NOT NULL
	// AUTO_INCREMENT, name VARCHAR(50), description VARCHAR(50), PRIMARY KEY
	// (task_id), FOREIGN KEY (todo_id) REFERENCES todo(todo_id))")
	// void createTaskTable();
	//
	// @SqlUpdate("INSERT INTO task (name, description, todo_id) VALUES ('first todo
	// name', 'this the first todo description.', 1)")
	// int addTask1();
	//
	// @SqlUpdate("INSERT INTO task (todo_id) VALUES (2)")
	// int addTask2();
	//
	// @SqlUpdate("INSERT INTO task (name, todo_id) VALUES ('second todo name', 2)")
	// int addTask3();
}
