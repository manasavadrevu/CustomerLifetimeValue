/** Customer Class stores the customer details and associated events like site visits,
 * image upload and orders.
 */
package com.shutterfly.clv;

import java.util.ArrayList;

public class Customer {
		
	/**
	 * last name of the customer
	 */
	private String last_name;
	
	/**
	 * Address City of the customer
	 */
	private String adr_city;
	
	/**
	 * Address State of the customer
	 */
	private String adr_state;
	
	/**
	 *  Event_Time(new/update) associated with customer 
	 */
	private String event_time;
	
	/**
	 * All Site visits for the customer
	 */
	private ArrayList <SiteVisit> siteVisits = new ArrayList<>();
	
	/**
	 * Details of all the images uploaded by the customer
	 */
	private ArrayList <UserImage> imageList = new ArrayList<>();
	
	/**
	 * Details of all the orders placed by the customer
	 */
	private ArrayList <Order> orderList = new ArrayList<>();

	
	/**
	 * get the last name of the customer
	 * @return last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * Sets the last name
	 * @param last_name last_name to set 
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * get the city of the customer
	 * @return adr_city
	 */
	public String getAdr_city() {
		return adr_city;
	}

	/**
	 * Sets the address city
	 * @param adr_city adr_city to set 
	 */
	public void setAdr_city(String adr_city) {
		this.adr_city = adr_city;
	}
	
	/**
	 * get the state of the customer
	 * @return adr_state
	 */
	public String getAdr_state() {
		return adr_state;
	}

	/**
	 * Sets the address state
	 * @param adr_state adr_state to set 
	 */
	public void setAdr_state(String adr_state) {
		this.adr_state = adr_state;
	}

	/**
	 * get the event time of the customer
	 * @return event_time
	 */
	public String getEvent_time() {
		return event_time;
	}

	/**
	 * Sets the event time
	 * @param event_time event_time to set 
	 */
	public void setEvent_time(String event_time) {
		this.event_time = event_time;
	}

	/**
	 * get all the site visit details of the customer
	 * @return siteVisits
	 */
	public ArrayList <SiteVisit> getSiteVisits() {
		return this.siteVisits;
	}

	/**
	 * Site Visit Event
	 * Adds to the list whenever the customer visits the site 
	 * @add siteVisit
	 */
	public void addSiteVisits(SiteVisit siteVisit) {
		if(siteVisit != null){
			this.siteVisits.add(siteVisit);
		}
	}
	
	/**
	 * get all the image details uploaded by the customer
	 * @return imageList
	 */
	public ArrayList <UserImage> getImageList() {
		return this.imageList;
	}
	
	/**
	 * Image Upload Event
	 * Adds to the list whenever the customer uploads images
	 * @add image
	 */
	public void addImage(UserImage userImage) {
		if(userImage != null){
			this.imageList.add(userImage);
		}
	}
	
	/**
	 * get the details of all the orders placed by the customer
	 * @return orderList
	 */
	public ArrayList <Order> getOrderList() {
		return this.orderList;
	}
	
	/**
	 * New Order Event
	 * Adds to the list whenever there is a new customer order
	 * @add order
	 */
	public void addOrder(Order order) {
		if(order != null){
			this.orderList.add(order);
		}
	}

	/**
	 * Update Order Event
	 * Adds to the list whenever the customer updates existing order
	 * @update order
	 */
	public void updateOrder(Order order) {
		if(order != null){
			int index = -1;
			for(Order item : orderList)
	        {
	            if(item.getKey().equals(order.getKey()))
	                index = orderList.indexOf(item);
	        }
			if(index != -1){
				this.orderList.set(index, order);
			}
		}
	}
}

