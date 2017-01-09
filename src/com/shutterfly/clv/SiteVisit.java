/** SiteVisit Class which stores and retrieve information about the site visits of the customer
 */
package com.shutterfly.clv;

public class SiteVisit {
	
	/**
	 * Site visit key
	 */
	private String key;
	
	/**
	 * Site Visit Time
	 */
	private String event_time;
	
	/**
	 * Site visit Description
	 */
	private String tag;
	
	
	/**
	 * get the unique site visit event
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the unique site visit event
	 * @param key key to set 
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * get the site visit time
	 * @return event_time
	 */
	public String getEvent_time() {
		return event_time;
	}

	/**
	 * Sets the site visit time
	 * @param event_time event_time to set 
	 */
	public void setEvent_time(String event_time) {
		this.event_time = event_time;
	}
	
	/**
	 * get the site visit descriptions
	 * @return tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Sets the site visit descriptions
	 * @param tag tag to set 
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
}
