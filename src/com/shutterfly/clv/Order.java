/** Order Class which stores and retrieve  the order details for each customer
 */
package com.shutterfly.clv;

public class Order {
	
	/**
	 * Unique Order Key 
	 */
	private String key;
	
	/**
	 * Time at which the order was placed
	 */
	private String event_time;
	
	/**
	 * Total Amount of the order
	 */
	private float total_amount;

	/**
	 * get the Unique Order Key 
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the unique order key
	 * @param key key to set 
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * get the time at which the order was placed
	 * @return event_time
	 */
	public String getEvent_time() {
		return event_time;
	}

	/**
	 * Sets the time at which the order was placed
	 * @param event_time event_time to set 
	 */
	public void setEvent_time(String event_time) {
		this.event_time = event_time;
	}

	/**
	 * get the total amount of the order
	 * @return total_amount
	 */
	public float getTotal_amount() {
		return total_amount;
	}

	/**
	 * Sets the total amount of the order
	 * @param total_amount total_amount to set 
	 */
	public void setTotal_amount(float total_amount) {
		this.total_amount = total_amount;
	}
}
