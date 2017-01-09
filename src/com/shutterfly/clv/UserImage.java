/** UserImage Class which stores and retrieves the image details the customer has uploaded
 */
package com.shutterfly.clv;

public class UserImage {
	
	
	/**
	 * Unique Image ID uploaded by the customer
	 */
	private String key;
	
	/**
	 * Time at which the image was uploaded
	 */
	private String event_time;
	
	/**
	 *  Make of the camera from which the image was taken
	 */
	private String camera_make;
	
	/**
	 *  Model of the camera from which the image was taken
	 */
	private String camera_model;

	/**
	 * get the Unique Image ID
	 * @return last_name
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the Unique Image ID 
	 * @param key key to set 
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * get the last name of the customer
	 * @return event_time
	 */
	public String getEvent_time() {
		return event_time;
	}

	/**
	 * Sets the last name
	 * @param event_time event_time to set 
	 */
	public void setEvent_time(String event_time) {
		this.event_time = event_time;
	}

	/**
	 * get the camera make of the image
	 * @return camera_make
	 */
	public String getCamera_make() {
		return camera_make;
	}

	/**
	 * Sets the camera make of the image
	 * @param camera_make camera_make to set 
	 */
	public void setCamera_make(String camera_make) {
		this.camera_make = camera_make;
	}

	/**
	 * get the camera model of the image
	 * @return camera_model
	 */
	public String getCamera_model() {
		return camera_model;
	}

	/**
	 * Sets the camera model of the image
	 * @param camera_model camera_model to set 
	 */
	public void setCamera_model(String camera_model) {
		this.camera_model = camera_model;
	}
}
