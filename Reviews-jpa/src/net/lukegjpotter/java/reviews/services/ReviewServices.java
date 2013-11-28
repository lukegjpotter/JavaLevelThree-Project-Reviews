package net.lukegjpotter.java.reviews.services;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.lukegjpotter.java.reviews.model.Review;

/**
 * @author lukegjpotter
 */
public class ReviewServices {

	@PersistenceContext(unitName = "Reviews-jpa")
	EntityManager entityManager;
	
	public Collection<Review> getAllProducts() {

		EntityManager entityManager = this.entityManager;
		entityManager.getTransaction().begin();

		Query allReviewsQuery = entityManager.createNamedQuery("getAllReviewsInDatabase");
		@SuppressWarnings("unchecked")
		List<Review> reviews = allReviewsQuery.getResultList();

		entityManager.getTransaction().commit();
		entityManager.close();

		return reviews;
	}
}
