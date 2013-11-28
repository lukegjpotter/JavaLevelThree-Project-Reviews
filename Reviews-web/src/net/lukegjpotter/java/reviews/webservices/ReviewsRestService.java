package net.lukegjpotter.java.reviews.webservices;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import net.lukegjpotter.java.reviews.ReviewEjb;
import net.lukegjpotter.java.reviews.ReviewEjbLocal;
import net.lukegjpotter.java.reviews.model.Review;

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
	
	@POST()
	@Path("AddReview")
	@Consumes("application/json")
	@Produces("text/plain")
	public Response addReview(Review review) {
		
		reviewEjb.addReview(review);
		
		return Response.ok().build();
	}
	
	@GET()
	@Path("/AllReviews")
	@Produces("application/json")
	public String getAllReviews() {
		
		return reviewEjb.getAllReviews().toString();
	}
	
	@GET()
	@Path("/AllReviewsByUser")
	@Produces("application/json")
	public String getAllReviewsByUser(@QueryParam("name") String reviewerName) {
		
		return reviewEjb.getAllReviews(reviewerName).toString();
	}
	
	@GET()
	@Path("/AllReviewsForProduct")
	@Produces("application/json")
	public String getAllReviewsForProduct(@QueryParam("make") String make, @QueryParam("model") String model) {
		
		return reviewEjb.getAllReviewsForProduct(make, model).toString();
	}
}
