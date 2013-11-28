package net.lukegjpotter.java.reviews;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.lukegjpotter.java.reviews.model.Product;
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
		
		if(review == null) {
			review = new Review(new ReviewUser("lukegjpotter", ""), new Product("Tefal", "Toaster 2000", 20.00), "This was decent", 50);
		}
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
			System.out.println(LOG_INFO + "Creating Query.");
			allReviewsQuery = entityManager.createNamedQuery("getAllReviewsInDatabase");
			System.out.println(LOG_INFO + "Getting Results List.");
		} catch (NullPointerException e) {
			System.out.println(LOG_ERROR + "There was an error. creating the Query from the EntityManager.");
			e.printStackTrace();
		}
		
		@SuppressWarnings("unchecked")
		List<Review> allReviews = allReviewsQuery.getResultList(); 
		
		System.out.println(LOG_INFO + "Returning Results List.");
		return allReviews;
	}

	@Override
	public List<Review> getAllReviews(String reviewerName) {
		
		checkForNullEntityManager();
		
		System.out.println(LOG_INFO + "Inside AllReviews(String).");
		
		Query allReviewsQuery = null;
		try {
			System.out.println(LOG_INFO + "Creating Query.");
			allReviewsQuery = entityManager.createNamedQuery("getAllReviewsFromReviewer");
			allReviewsQuery.setParameter("name", reviewerName);
		} catch (NullPointerException e) {
			System.out.println(LOG_ERROR + "There was an error. creating the Query from the EntityManager.");
			e.printStackTrace();
		}
		
		System.out.println(LOG_INFO + "Getting Results List.");
		@SuppressWarnings("unchecked")
		List<Review> allReviews = allReviewsQuery.getResultList();
		
		System.out.println(LOG_INFO + "Returning Results List.");
		return allReviews;
	}
	
	@Override
	public List<Review> getAllReviewsForProduct(String make, String model) {
		
		checkForNullEntityManager();
		
		System.out.println(LOG_INFO + "Inside getAllReviewsForProduct(String, String).");
		
		Query allReviewsQuery = null;
		try {
			System.out.println(LOG_INFO + "Creating Query.");
			allReviewsQuery = entityManager.createNamedQuery("getAllReviewsForProduct");
			allReviewsQuery.setParameter("make", make);
			allReviewsQuery.setParameter("model", model);
		} catch (NullPointerException e) {
			System.out.println(LOG_ERROR + "There was an error. creating the Query from the EntityManager.");
			e.printStackTrace();
		}
		
		System.out.println(LOG_INFO + "Getting Results List.");
		@SuppressWarnings("unchecked")
		List<Review> allReviews = allReviewsQuery.getResultList(); 
		
		System.out.println(LOG_INFO + "Returning Results List.");
		return allReviews;
	}
	
	// ---------- Utility Methods ---------- //
	private void checkForNullEntityManager() {
		if(entityManager == null) {
			System.out.println(LOG_ERROR + "Entity Manager is null. Brace yourselves, the NullPointerExceptions are coming. NedStark.png");
		}
	}
}
