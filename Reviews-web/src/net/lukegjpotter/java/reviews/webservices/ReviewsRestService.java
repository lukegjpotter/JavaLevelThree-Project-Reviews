package net.lukegjpotter.java.reviews.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

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
		return reviewEjb.toString();
	}
	
	@POST()
	@Path("AddReview")
	@Consumes("application/json")
	@Produces("text/plain")
	public Response addReview(Review review) {
		
		if(reviewEjb.reviewUserExists(review.getReviewUser())
				&& reviewEjb.reviewUserCredentialsCorrect(review.getReviewUser())) {
			
			reviewEjb.addReview(review);
			return Response.ok().build();
			
		} else {
			
			return Response.notModified(
					"The Review User's credentials are incorrect or the Review User does not exist, please register.")
					.build();
		}
	}
	
	@GET()
	@Path("/AllReviews")
	@Produces("application/json")
	public List<Review> getAllReviews() {
		
		return reviewEjb.getAllReviews();
	}
	
	@GET()
	@Path("/AllReviewsByUser")
	@Produces("application/json")
	public List<Review> getAllReviewsByUser(@QueryParam("name") String reviewerName) {
		
		return reviewEjb.getAllReviews(reviewerName);
	}
	
	@GET()
	@Path("/AllReviewsForProduct")
	@Produces("application/json")
	public List<Review> getAllReviewsForProduct(@QueryParam("make") String make, @QueryParam("model") String model) {
		
		return reviewEjb.getAllReviewsForProduct(make, model);
	}
}
