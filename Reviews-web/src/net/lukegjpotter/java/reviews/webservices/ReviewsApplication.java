package net.lukegjpotter.java.reviews.webservices;

import java.util.Set;
import java.util.HashSet;
import javax.ws.rs.core.Application;

public class ReviewsApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	public ReviewsApplication(){
	     singletons.add(new ReviewsRestService());
	}
	@Override
	public Set<Class<?>> getClasses() {
	     return empty;
	}
	@Override
	public Set<Object> getSingletons() {
	     return singletons;
	}
}
