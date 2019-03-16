package com.leanix.simple.restservice.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.leanix.simple.restservice.core.Todo;
import com.leanix.simple.restservice.dao.TodoDao;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

	/**
     * The DAO object to manipulate todos.
     */
	private TodoDao todoDao;
	
	
	public TodoResource(TodoDao todoDao) {
		this.todoDao = todoDao;
	}

	
	/**
	 * GET /todos
	 * @return a list of all TODOs
	 * */
	@GET
	@Timed
	@UnitOfWork		//Required for transactional database access
	public List<Todo> getTodos() {
		return todoDao.findAll();
	}

	/**
	 * GET /todos/notcompleted
	 * @return a list of all not completed TODOs
	 * */
	@Path("/notcompleted")
	@GET
	@Timed
	@UnitOfWork		//Required for transactional database access
	public List<Todo> getNotCompletedTodos() {
		return todoDao.findAllByStatus((byte) 0);
	}

	/**
	 * GET /todos/completed
	 * @return a list of all completed TODOs
	 * */
	@Path("/completed")
	@GET
	@Timed
	@UnitOfWork		//Required for transactional database access
	public List<Todo> getCompletedTodos() {
		return todoDao.findAllByStatus((byte) 1);
	}

	/**
	 * GET /todos/deleted
	 * @return a list of all deleted TODOs
	 * */
	@Path("/deleted")
	@GET
	@Timed
	@UnitOfWork		//Required for transactional database access
	public List<Todo> getDeletedTodos() {
		return todoDao.findAllByStatus((byte) 2);
	}

	/**
	 * GET /todos/{id}
	 * @return a todo with given id
	 * */
	@Path("/{id}")
	@GET
	@Timed
	@UnitOfWork
	public Optional<Todo> getTodo(
			@PathParam("id") IntParam id
	) {
		return todoDao.findById(id.get());
	}


	/**
	 * POST /todos
	 * Creates a new todo
	 * @param todo Expects a todo (without id)
	 * @return a new todo with ID
	 * */
	@POST
	@Timed
	@UnitOfWork
	public Optional<Todo> addTodo(@NotNull @Valid final Todo todo) {
		return todoDao.createTodo(todo);
	}

	/**
	 * PUT /todos/{id}
	 * Updates existing todo
	 * @param id todo id
	 * @param todo object to be edited
	 * @return todo object
	 * */
	@Path("/{id}")
	@PUT
	@Timed
	@UnitOfWork
	public Optional<Todo> overwriteTodo(@PathParam("id") final int id, @NotNull @Valid final Todo todo) {
		return todoDao.updateTodo(id, todo);
	}

	/**
	 * DELETE /todos/{id}
	 * @param id todo id to delete
	 * @return todo object that is deleted
	 * */
	@Path("/{id}")
	@DELETE
	@Timed
	@UnitOfWork
	public Todo deleteTodo(@PathParam("id") final int id) {
		return todoDao.deleteTodo(id);
	}

	/**
	 * GET /todos/search
     * Looks for todos whose content contains the passed
     * parameter as a substring. If name argument was not passed, returns no todos stored in the database.
     *
     * @param value query parameter
     * @return list of todos whose content contains the passed parameter as a substring or no todos.
     */
	@Path("/search")
	@GET
    @UnitOfWork
    public List<Todo> findByName(
            @QueryParam("value") Optional<String> value
    ) {
        if (value.isPresent()) {
            return todoDao.findByName(value.get());
        } else {
        	return new ArrayList<Todo>();
        }
	}
}
