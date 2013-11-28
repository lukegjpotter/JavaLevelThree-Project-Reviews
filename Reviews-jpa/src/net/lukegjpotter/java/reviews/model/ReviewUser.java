package net.lukegjpotter.java.reviews.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ReviewUser
 *
 */
@Entity
@Table(name="ReviewUser", schema = "REVIEWS")
public class ReviewUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	private String userName, password;
	
	// ---------- Constructors --------- //
	public ReviewUser() {
	}
	
	public ReviewUser(String un, String pw) {
		setUserName(un);
		setPassword(pw);
	}
	
	public ReviewUser(int i, String un, String pw) {
		setUserId(i);
		setUserName(un);
		setPassword(pw);
	}

	public long getUserId() {
		return userId;
	}

	// ---------- Getters and Setters --------- //
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	// ---------- Utility Methods --------- //
}
