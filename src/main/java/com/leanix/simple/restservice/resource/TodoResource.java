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
import com.leanix.simple.restservice.representation.TodoRepr;

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


	@Path("/{id}")
	@GET
	@Timed
	@UnitOfWork
	public Optional<Todo> getTodo(
			@PathParam("id") IntParam id
	) {
		return todoDao.findById(id.get());
	}
	
	@GET
	@Timed
	@UnitOfWork
	public List<Todo> getTodos() {
		return todoDao.findAll();
	}

	@POST
	@Timed
	public TodoRepr addTodo(@NotNull @Valid final TodoRepr todo) {
		return new TodoRepr();
	}

	@Path("/{id}")
	@PUT
	@Timed
	public TodoRepr overwriteTodo(@PathParam("id") final int id, @NotNull @Valid final TodoRepr todo) {
		return new TodoRepr();
	}

	@Path("/{id}")
	@DELETE
	@Timed
	public TodoRepr deleteTodo(@PathParam("id") final int id) {
		return new TodoRepr();
	}

	/**
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
//            return todoDao.findAll();
        	return new ArrayList<Todo>();
        }
}
}
