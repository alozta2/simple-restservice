package com.leanix.simple.restservice;

import org.junit.Rule;
import org.junit.Test;

import com.leanix.simple.restservice.resource.TodoResource;

import io.dropwizard.testing.junit.ResourceTestRule;

public class SimpleRestServiceApplicationTest {

//	@Rule
//    public ResourceTestRule resource = ResourceTestRule.builder().addResource(new SimpleRestServiceResource()).build();
	
	
//	@Test
//    public void testGetGreeting() {
//        String expected = "Hello world!";
//        //Obtain client from @Rule.
//        Client client = resource.client();
//        //Get WebTarget from client using URI of root resource.
//        WebTarget helloTarget = client.target("http://localhost:8080/hello");
//        //To invoke response we use Invocation.Builder
//        //and specify the media type of representation asked from resource.
//        Invocation.Builder builder = helloTarget.request(MediaType.TEXT_PLAIN);
//        //Obtain response.
//        Response response = builder.get();
//
//        //Do assertions.
//        assertEquals(Response.Status.OK, response.getStatusInfo());
//        String actual = response.readEntity(String.class);
//        assertEquals(expected, actual);
//	}
}
