package com.leanix.simple.restservice;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.leanix.simple.restservice.core.Todo;
import com.leanix.simple.restservice.dao.TodoDao;
import com.leanix.simple.restservice.resource.TodoResource;

public class SimpleRestServiceApplicationTest {
	
	private final TodoDao todoDAO = new TodoDao(SimpleRestServiceApplication.getHibernateBundle().getSessionFactory());
	private TodoResource todoResource;
	
	@Before
	public void initialize() {
		todoResource = new TodoResource(todoDAO);
	}
	
	@Test
    public void checkInitialTodoCount() {
        List<Todo> result = todoResource.getTodos();
        assertTrue(result.size() == 3);
    }
}
