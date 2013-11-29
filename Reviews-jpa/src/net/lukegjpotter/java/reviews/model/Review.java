package net.lukegjpotter.java.reviews.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Review
 *
 */
@NamedQueries({ @NamedQuery(name = "getAllReviewsInDatabase",   query = "SELECT r FROM Review r") })
@Entity
@Table(name = "REVIEW", schema = "REVIEWS")
@XmlRootElement(name = "review")
@XmlAccessorType(XmlAccessType.FIELD)
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reviewId;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) @JoinColumn(name = "reviewUserId")
	private ReviewUser reviewUser;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) @JoinColumn(name = "productId")
	private Product product;
	private String reviewText;
	private int rating; // Out of 100.
	
	// ---------- Constructors --------- //
	public Review() {}
	
	public Review(ReviewUser ru, Product p, String text, int rating) {
		setReviewUser(ru);
		setProduct(p);
		setReviewText(text);
		setRating(rating);
	}
	
	public Review(long id, ReviewUser ru, Product p, String text, int rating) {
		setReviewId(id); 
		setReviewUser(ru);
		setProduct(p);
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
		return product.toString() + " " + reviewText + ". Rating: " + rating + "%. Reviewer: " + reviewUser.toString() + ".";
	}
}
