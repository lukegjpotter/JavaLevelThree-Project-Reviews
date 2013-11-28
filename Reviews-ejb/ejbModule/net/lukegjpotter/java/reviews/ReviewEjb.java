package net.lukegjpotter.java.reviews;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.lukegjpotter.java.reviews.model.Review;

/**
 * Session Bean implementation class ReviewEjb
 */
@Stateless
public class ReviewEjb implements ReviewEjbLocal {

	@PersistenceContext(unitName = "Reviews-jpa")
	EntityManager entityManager;
	
    public ReviewEjb() {
    }
    
    @PostConstruct
    public void onPostConstruct() {
    }

    @PreDestroy
    public void onPreDestroy() {
    	
    	entityManager = null;
    }

	@Override
	public void addReview(Review review) {
		
		System.out.println("    [ReviewEJB]    Inside AddReview.");
		
		if(review == null) {
			review = new Review("Luke", "This was decent", 50);
		}
		
		System.out.println("    [ReviewEJB]    Trying to persist: " + review.toString());
    	
		try {
			// Create a review object.
			entityManager.persist(review);
			
			System.out.println("    [ReviewEJB]    Persisted the following Review: " + review.toString());
		} catch(NullPointerException e) {
			
			System.out.println("    [ReviewEJB]    Unable to Persist: " + review.toString());
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Review> getAllReviews() {
		
		System.out.println("    [ReviewEJB]    Inside AllReviews.");
		System.out.println("    [ReviewEJB]    Creating Query.");
		Query allReviewsQuery = entityManager.createNamedQuery("getAllReviewsInDatabase");
		System.out.println("    [ReviewEJB]    Getting Results List.");
		@SuppressWarnings("unchecked")
		List<Review> allReviews = allReviewsQuery.getResultList(); 
		System.out.println("    [ReviewEJB]    Returning Results List.");
		return allReviews;
	}

	@Override
	public List<Review> getAllReviews(String reviewerName) {
		
		System.out.println("    [ReviewEJB]    Inside AllReviews.");
		System.out.println("    [ReviewEJB]    Creating Query.");
		Query allReviewsQuery = entityManager.createNamedQuery("getAllReviewsFromReviewer");
		allReviewsQuery.setParameter("name", reviewerName);
		System.out.println("    [ReviewEJB]    Getting Results List.");
		@SuppressWarnings("unchecked")
		List<Review> allReviews = allReviewsQuery.getResultList(); 
		System.out.println("    [ReviewEJB]    Returning Results List.");
		return allReviews;
	}
}
