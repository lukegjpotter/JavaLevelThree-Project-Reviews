package net.lukegjpotter.java.reviews.webservices;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import net.lukegjpotter.java.reviews.ReviewEjb;
import net.lukegjpotter.java.reviews.ReviewEjbLocal;

@Path("/ReviewsApplication")
public class ReviewsRestService {

	@Inject
	ReviewEjbLocal reviewEjb;
	
	@GET()
	@Produces("text/plain")
	public String sayHello() {
	    return "Hello World!";
	}
	
	@GET()
	@Path("/Test")
	@Produces("text/plain")
	public String doSomething() {
		reviewEjb = new ReviewEjb();
		return reviewEjb.toString();
	}
}
