package com.jpt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



// TODO: Auto-generated Javadoc
/**
 * The Class Order.
 *
 * @author raaju_ajay
 */

@Entity
@Table(name="userOrder")
public class UserOrder implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	private int id;
	
	/** The order name. */
	@Column
	private String orderName;
	
	/** The order description. */
	@Column
	private String orderDescription;
	
	/** The total price. */
	@Column
	private double totalPrice;
	
	//Mapping ManyToOne
	@ManyToOne
	@JoinColumn(name="user_id", insertable=false, updatable=false)
	private User user;
	
	@Column(name="user_id")
	private int userId;
 
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	public int getUserid() {
		return userId;
	}

	public void setUserid(int userid) {
		this.userId = userid;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the order number.
	 *
	 * @return the order number
	 */
	public String getOrderName() {
		return orderName;
	}

	/**
	 * Sets the order number.
	 *
	 * @param orderNumber
	 *            the new order number
	 */
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	/**
	 * Gets the order description.
	 *
	 * @return the order description
	 */
	public String getOrderDescription() {
		return orderDescription;
	}

	/**
	 * Sets the order description.
	 *
	 * @param orderDescription
	 *            the new order description
	 */
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	/**
	 * Gets the total price.
	 *
	 * @return the total price
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Sets the total price.
	 *
	 * @param totalPrice
	 *            the new total price
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime
				* result
				+ ((orderDescription == null) ? 0 : orderDescription.hashCode());
		result = prime * result
				+ ((orderName == null) ? 0 : orderName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserOrder other = (UserOrder) obj;
		if (id != other.id)
			return false;
		if (orderDescription == null) {
			if (other.orderDescription != null)
				return false;
		} else if (!orderDescription.equals(other.orderDescription))
			return false;
		if (orderName == null) {
			if (other.orderName != null)
				return false;
		} else if (!orderName.equals(other.orderName))
			return false;
		if (Double.doubleToLongBits(totalPrice) != Double
				.doubleToLongBits(other.totalPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserOrder [ orderName=" + orderName
				+ ", orderDescription=" + orderDescription + ", totalPrice="
				+ totalPrice + "]";
	}
	
	

}
