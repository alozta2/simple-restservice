package com.leanix.simple.restservice;

import com.leanix.simple.restservice.core.Task;
import com.leanix.simple.restservice.core.Todo;
import com.leanix.simple.restservice.dao.TodoDao;
import com.leanix.simple.restservice.health.SimpleRestServiceHealthCheck;
import com.leanix.simple.restservice.resource.TodoResource;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SimpleRestServiceApplication extends Application<SimpleRestServiceConfiguration> {
	
	/**
     * Specifying the entity classes and how to get a DataSourceFactory from your configuration subclass.
     */
    private final HibernateBundle<SimpleRestServiceConfiguration> hibernateBundle
            = new HibernateBundle<SimpleRestServiceConfiguration>(
            		//List all entity classes here separated with commas
                    Todo.class,
                    Task.class
            ) {

        public DataSourceFactory getDataSourceFactory(SimpleRestServiceConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }

    };
	
	public static void main(String[] args) throws Exception {
        new SimpleRestServiceApplication().run(args);
    }
	
	@Override
    public String getName() {
        return "SimpleRestServiceApplication";
    }

    @Override
    public void initialize(final Bootstrap<SimpleRestServiceConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }
	

	@Override
	public void run(SimpleRestServiceConfiguration configuration, Environment environment) throws Exception {
		//Registering health checks
		final SimpleRestServiceHealthCheck healthCheck = new SimpleRestServiceHealthCheck(configuration.getContent());
		//Create Todo DAO.
        final TodoDao todoDAO = new TodoDao(hibernateBundle.getSessionFactory());
        
        //Register resources
		environment.healthChecks().register("content", healthCheck);
        environment.jersey().register(new TodoResource(todoDAO));
        
        
        //Create Jersey client.
//        final Client client = new JerseyClientBuilder(environment)
//                .using(configuration.getJerseyClientConfiguration())
//                .build(getName());
        //Register authenticator.
//        environment.jersey().register(new AuthDynamicFeature(
//                new BasicCredentialAuthFilter.Builder<User>()
//                        .setAuthenticator(new GreetingAuthenticator(configuration.getLogin(),
//                                configuration.getPassword()))
//                        .setRealm("SECURITY REALM")
//                        .buildAuthFilter()));
        
//        environment.jersey().register(RolesAllowedDynamicFeature.class);
//        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        //Register a secured resource.
//        environment.jersey().register(new SecuredHelloResource());
        //Register a database-backed resource.
        //Register a resource using Jersey client.
//        environment.jersey().register(
//                new ConverterResource(
//                        client,
//                        configuration.getApiURL(),
//                        configuration.getApiKey())
//        );
	}
}
