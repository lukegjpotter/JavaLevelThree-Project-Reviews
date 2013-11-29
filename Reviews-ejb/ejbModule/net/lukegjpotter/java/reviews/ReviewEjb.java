package net.lukegjpotter.java.reviews;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.lukegjpotter.java.reviews.model.Review;
import net.lukegjpotter.java.reviews.model.ReviewUser;

/**
 * Session Bean implementation class ReviewEjb
 */
@Stateless
public class ReviewEjb implements ReviewEjbLocal {

	@PersistenceContext(unitName = "Reviews-jpa")
	private EntityManager entityManager;// = Persistence.createEntityManagerFactory("Reviews-jpa").createEntityManager();
	
	// Logging prefix strings.
	private static final String LOG_MESSAGE_PREFIX = "    [ReviewEJB]";
	private static final String LOG_INFO  = LOG_MESSAGE_PREFIX + "[info]     ";
	private static final String LOG_ERROR = LOG_MESSAGE_PREFIX + "[error]    ";
	
    public ReviewEjb() {
    }

    // ---------- Bean Lifecycle Methods ---------- //
    @PostConstruct
    public void onPostConstruct() {
    	
    	checkForNullEntityManager();
    }

    @PreDestroy
    public void onPreDestroy() {
    	
    	entityManager = null;
    }

    // ---------- Services Methods ---------- //
	@Override
	public void addReview(Review review) {
		
		checkForNullEntityManager();
		
		System.out.println(LOG_INFO + "Inside AddReview(Review).");

		System.out.println(LOG_INFO + "Trying to persist: " + review.toString());
		
		try {
			// Create a review object.
			entityManager.persist(review);
			System.out.println(LOG_INFO + "Persisted the following Review: " + review.toString());
		} catch(NullPointerException e) {
			System.out.println(LOG_ERROR + "Unable to Persist: " + review.toString());
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Review> getAllReviews() {
		
		checkForNullEntityManager();
		
		System.out.println(LOG_INFO + "Inside AllReviews().");
		
		Query allReviewsQuery = null;
		try {
			allReviewsQuery = entityManager.createNamedQuery("getAllReviewsInDatabase");
		} catch (NullPointerException e) {
			System.out.println(LOG_ERROR + "There was an error. creating the Query from the EntityManager.");
			e.printStackTrace();
		}
		
		@SuppressWarnings("unchecked")
		List<Review> allReviews = allReviewsQuery.getResultList(); 
		
		return allReviews;
	}

	@Override
	public List<Review> getAllReviews(String reviewerName) {
		
		System.out.println(LOG_INFO + "Inside getAllReviews(String).");
		
		List<Review> allReviews = getAllReviews();
		
		List<Review> userReviews = new ArrayList<Review>();
		
		for(Review review: allReviews) {
			
			if(review.getReviewUser().getUserName().equals(reviewerName)) {
				userReviews.add(review);
			}
		}
		
		return userReviews;
	}
	
	@Override
	public List<Review> getAllReviewsForProduct(String make, String model) {
				
		System.out.println(LOG_INFO + "Inside getAllReviewsForProduct(String, String).");
		
		List<Review> allReviews = getAllReviews();
		
		List<Review> productReviews = new ArrayList<Review>();
		
		for(Review review: allReviews) {
			
			if(review.getProduct().getMake().equals(make)
					&& review.getProduct().getModel().equals(model)) {
				productReviews.add(review);
			}
		}
		
		return productReviews;
	}
	
	@Override
	public boolean reviewUserExists(ReviewUser reviewUser) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean reviewUserCredentialsCorrect(ReviewUser reviewUser) {
		// TODO Auto-generated method stub
		return true;
	}
	
	// ---------- Utility Methods ---------- //
	private void checkForNullEntityManager() {
		if(entityManager == null) {
			System.out.println(LOG_ERROR + "Entity Manager is null. Brace yourselves, the NullPointerExceptions are coming. NedStark.png");
		}
	}
}
