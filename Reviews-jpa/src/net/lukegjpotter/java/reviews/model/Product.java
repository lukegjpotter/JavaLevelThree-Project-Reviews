package net.lukegjpotter.java.reviews.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity
@Table(name="Product", schema = "REVIEWS")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	private String make, model;
	private double priceInUsd; // Price in US Dollars.
	
	// ---------- Constructors --------- //
	public Product() {
	}
	
	public Product(String make, String model, double price) {
		setMake(make);
		setModel(model);
		setPriceInUsd(price);
	}
	
	public Product(long id, String make, String model, double price) {
		setProductId(id);;
		setMake(make);
		setModel(model);
		setPriceInUsd(price);
	}

	// ---------- Getters and Setters --------- //
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPriceInUsd() {
		return priceInUsd;
	}

	public void setPriceInUsd(double priceInUsd) {
		this.priceInUsd = priceInUsd;
	}
	
	// ---------- Utility Methods --------- //
	@Override
	public String toString() {
		
		return make + " " + model + " $" + priceInUsd;
	}
}
