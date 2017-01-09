/** LTVOutput Class allows the program to work with the customer LTV values
 */
package com.shutterfly.clv;

public class LTVOutput {
	
	/**
	 * Customer ID
	 */
	private String customer_id;
	
	/**
	 * last name of the customer
	 */
    private String name;
    
    /**
	 * Calculated Simple LTV value of the customer
	 */
	private float value;
    
	
	   /**
		 * Sets CustomerID, name and LTV value
		 */
	public LTVOutput(String customer_id,String name,float value){
    	this.customer_id=customer_id;
    	this.name=name;
    	this.value=value;
    }
	
	/**
	 * get the customer_id of the customer
	 * @return customer_id
	 */
    public String getCustomer_id() {
		return customer_id;
	}
    
    /**
	 * get the last name of the customer
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * get the Simple LTV of the customer
	 * @return value
	 */
	public float getValue() {
		return value;
	}
}
