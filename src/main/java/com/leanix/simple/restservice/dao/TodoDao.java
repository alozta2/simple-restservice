package com.leanix.simple.restservice.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import com.leanix.simple.restservice.core.Task;
import com.leanix.simple.restservice.core.Todo;

import io.dropwizard.hibernate.AbstractDAO;

public class TodoDao extends AbstractDAO<Todo> {
	
	private static Todo invisibleRootTodo = null;		//new relation for deleted tasks will be assigned to this dummy todo

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

	@SuppressWarnings("unchecked")
	public List<Todo> findAllByStatus(byte status) {
		return list(namedQuery("com.leanix.simple.restservice.core.Todo.findAllByStatus").setParameter("status", status));
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
	
	/**
	 * Persists given todo into database
	 * @param todo TODO to be persisted
	 * @return Object of persisted TODO
	 * */
	public Optional<Todo> createTodo(Todo todo) {
		//Jackson serialization causes stack overflow because of two classes (todo and task) referencing each other
		//I've had used JSONIgnore in the task entity class to prevent accepting todo objects as json
		//So it is wise to attach todo relation to tasks back before saving
//		for(Task t : todo.getTasks()) {
//			t.setTodo(todo);
//		}
		todo.registerTaskTodoRelation();
		int savedTodoId = (Integer) this.currentSession().save(todo);
		return findById(savedTodoId);
	}
	
	/**
	 * Updates contents of given TODO.
	 * Any missing Todo.task will register as deleted.
	 * */
	public Optional<Todo> updateTodo(final int id, final Todo todo) {
		if(invisibleRootTodo == null)
			invisibleRootTodo = findAllByStatus((byte) 7).get(0);	//new relation for deleted tasks will be assigned to this dummy todo
		
		//Update todo
		Todo t = findById(id).get();
		t.setName(todo.getName());
		t.setDescription(todo.getDescription());
		t.setStatus(todo.getStatus());
		//Update tasks. Deleted tasks will be connected to dummy todo for deletion simulation.
		for(Task task : t.getTasks()) {
			task.setTodo(invisibleRootTodo);
		}
		t.setTasks(todo.getTasks());
		
		t.registerTaskTodoRelation();
		this.currentSession().save(invisibleRootTodo);
		int savedTodoId = (Integer) this.currentSession().save(t);
		return findById(savedTodoId);
	}

	/**
	 * Deletes a todo with given id.
	 * @param id todo id to be delted
	 * @return deleted object
	 * */
	public Todo deleteTodo(final int id) {
		Todo t = findById(id).orElse(new Todo());
		t.setStatus((byte) 2);
		this.currentSession().delete(t);
		return t;
	}
}
