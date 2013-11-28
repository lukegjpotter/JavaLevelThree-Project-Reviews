package net.lukegjpotter.java.reviews.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Review
 *
 */
@NamedQueries({
	@NamedQuery(name = "getAllReviewsInDatabase", query = "SELECT r FROM Review r"),
	@NamedQuery(name = "getAllReviewsFromReviewer", query = "SELECT r FROM Review r WHERE r.reviewerName = :name")
})
@Entity
@Table(name = "REVIEW", schema = "REVIEWS")
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reviewId;
	private String reviewerName, reviewText;
	private int rating; // Out of 100.
	
	// ---------- Constructors --------- //
	public Review() {}
	
	public Review(String name, String text, int rating) {
		setReviewerName(name); 
		setReviewText(text);
		setRating(rating);
	}
	
	public Review(long id, String name, String text, int rating) {
		setReviewId(id);
		setReviewerName(name); 
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

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
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
		return this.reviewText + ". Rating: " + this.rating + "%. Review Author: " + this.reviewerName + ".";
	}
}
