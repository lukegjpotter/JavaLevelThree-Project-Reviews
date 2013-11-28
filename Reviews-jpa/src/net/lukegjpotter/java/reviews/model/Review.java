package net.lukegjpotter.java.reviews.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Review
 *
 */
@NamedQueries({
	@NamedQuery(name = "getAllReviewsInDatabase", query = "SELECT r FROM Review r"),
	@NamedQuery(name = "getAllReviewsFromReviewer", query = "SELECT r FROM Review r, ReviewUser ru WHERE ru.userName = :name")
})
@Entity
@Table(name = "REVIEW", schema = "REVIEWS")
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reviewId;
	private ReviewUser reviewUser;
	private String reviewText;
	private int rating; // Out of 100.
	
	// ---------- Constructors --------- //
	public Review() {}
	
	public Review(ReviewUser ru, String text, int rating) {
		setReviewUser(ru);
		setReviewText(text);
		setRating(rating);
	}
	
	public Review(long id, ReviewUser ru, String text, int rating) {
		setReviewId(id); 
		setReviewUser(ru);
		setReviewText(text);
		setRating(rating);
	}

	// ---------- Getters and Setters --------- //
	public long getReviewId() {
		return reviewId;
	}

	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}

	public ReviewUser getReviewUser() {
		return reviewUser;
	}

	public void setReviewUser(ReviewUser reviewUser) {
		this.reviewUser = reviewUser;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
   
	// ---------- Utility Methods --------- //
	@Override
	public String toString() {
		return this.reviewText + ". Rating: " + this.rating + "%. Reviewer: " + this.reviewUser.getUserName() + ".";
	}
}
