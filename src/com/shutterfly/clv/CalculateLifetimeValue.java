/** The CalculateLifetimeValue class implements an application that is used to
 * 	ingest event data in-memory based on the event type and event verb read from an input file 
 *  in JSON format. This data is then used to calculate Customer Life Time Value and is Output
 *  to a file for the top X customers 
 */
package com.shutterfly.clv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author manasa
 *
 */
public class CalculateLifetimeValue {
	
	private static HashMap<String, Customer> customerMap = new HashMap<>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Read the input file into a JSONArray 
		JSONArray eventsArray = readInputFile("input\\input.txt");
		
		/**
		 * Iterate over each input line and store into a JSONObject
		 *  Each line which is considered as an event is ingested in the application
		 */
		
		for (Object event : eventsArray){
			JSONObject eventJSONFormat = (JSONObject) event;
			ingest(eventJSONFormat);		
		}
		
		/**
		 * Method to Calculate Simple Life Time Value of Top 10 Customers 
		 * 
		 */
		topXSimpleLTVCustomers(10);
	}
		
	
	/**
	 * Method to Calculate Simple Life Time Value of Top 10 Customers 
	 * @param x - No. of Top customers 
	 */
	private static void topXSimpleLTVCustomers(int x) {
		
		ArrayList<LTVOutput> LTVOutputList = new ArrayList<>();
		
		for (Entry<String, Customer> iterant : customerMap.entrySet()) {
			
			Customer customer = iterant.getValue();
		    ArrayList<Order> orders = customer.getOrderList();
		    
		    float totalExpenditure = 0;
		    
		    /** Order has the latest records of customer orders
		     * Count of orders will give the number of unique orders placed by
		     * by the customer
			 */
		    int numberOfOrders = orders.size();
		    
		    /** calculates the total expenditure spent by the customer in a week
			 */
		    for(Order order : orders){
		    	totalExpenditure = totalExpenditure+ order.getTotal_amount();
		    }
		    
		    /** Number of Site visits by the customer in a week
			 */
		    int noOfSiteVisits =  customer.getSiteVisits().size();
		    
		    /** Average lifespan for Shutterfly is 10 years as given 
			 */
		    int averageCustomerLifeSpan = 10;
		    
		    float customerLTV = calculateLTV(totalExpenditure, numberOfOrders, noOfSiteVisits, averageCustomerLifeSpan);
		    
		    LTVOutputList.add(new LTVOutput(iterant.getKey(), customer.getLast_name(), customerLTV));
		}
		
		
		 /** Gives a Sorted list of customers and LTV values in ascending order
		 */
		Collections.sort(LTVOutputList, new Comparator<LTVOutput>() {
		  
			@Override
		    public int compare(LTVOutput o1, LTVOutput o2) {
		    	
				Float firstValue = new Float(o1.getValue());
		    	Float secondValue = new Float(o2.getValue());
		        
		    	return firstValue.compareTo(secondValue);
		    
			}
		});
		
		 /** Sorts the list in descending order
		 */
		Collections.reverse(LTVOutputList);
		
		/** The output is store in a text file which is comma seperated containing
		 * Customer ID, Customer Last Name and Simple LTV Value order by LTV in 
		 * descending order
		 */
		try {
			
			FileWriter fw = new FileWriter("output\\output.txt");	 
			
			for (int i = 0; i < x && i < LTVOutputList.size(); i++) {
				LTVOutput currentIndexOutput = LTVOutputList.get(i);
				
				fw.write(currentIndexOutput.getCustomer_id() + "," + currentIndexOutput.getName() + "," + currentIndexOutput.getValue());
				
				fw.write(System.lineSeparator());
			}
 
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/** This method hold the formula compute the Simple LTV
	 * @param totalExpenditure, numberOfOrders, noOfSiteVisits, averageCustomerLifeSpan
	 * @return LTV - Lifetime Value
	 */
	private static float calculateLTV(float totalExpenditure, int numberOfOrders, int noOfSiteVisits, int averageCustomerLifeSpan){
		
		float LTV = (float) 52*averageCustomerLifeSpan*(totalExpenditure/numberOfOrders)*noOfSiteVisits;
		
		if(Float.isFinite(LTV)){
			return LTV;
		} else{
			return 0;
		}
	}

	/**
	 * This method is used to update the data based on the type of event
	 * @param event
	 */
	public static void ingest(JSONObject event){
		
		//Read and Store Event Types - Customer, Site-Visit, Image, Order
		String eventType = (String)event.get("type");
		
		//Read and Store Event Verbs - New, Update, Upload based on the event 
		String eventVerb = (String)event.get("verb");
		
		
		switch(eventType){
		
		/**
		 * The code is executed when the event type is a customer
		 *  When the event verb is new, a new customer object is created 
		 *  and the details are set. 
		 *  When the event verb is update, the existing values in the data container 
		 *  are replaced with the updated values
		 */
		case "CUSTOMER":
				
			if(eventVerb.equals("NEW") || eventVerb.equals("UPDATE")){
					String customerId = (String)event.get("key");
					Customer customer = customerMap.get(customerId);
					
					if(customer == null){
						customer = new Customer();
					}
					
					customer.setLast_name((String)event.get("last_name"));
					customer.setAdr_city((String)event.get("adr_city"));
					customer.setAdr_state((String)event.get("adr_state"));
					customer.setEvent_time((String)event.get("event_time"));
					
					customerMap.put(customerId, customer);
					
				} else{
					System.out.println("Unrecognized Verb");
				}
				
			break;
			
			/**
			 * The code is executed when the event type is a SITE_VISIT
			 *  When the event verb is new, site_visit details are read
			 *  and stored in the container against the customer 
			 */	
		 case "SITE_VISIT":
			 
				if(eventVerb.equals("NEW")){
					SiteVisit siteVisit = new SiteVisit();
					siteVisit.setEvent_time((String)event.get("event_time"));
					siteVisit.setKey((String)event.get("key"));
					siteVisit.setTag((String)event.get("tag"));
					
					String customerId = (String)event.get("customer_id");
					Customer customer = customerMap.get(customerId);
					
					customer.addSiteVisits(siteVisit);
					
				} else{
					System.out.println("Unrecognized Verb");
				}
				
				break;
			
				/**
				 * The code is executed when the event type is a IMAGE
				 *  When the event verb is upload, that is when the customer upload a new 
				 *  image, the image details are read and tagged for the customer
				 */	
			case "IMAGE":
				
				if(eventVerb.equals("UPLOAD")){				
					UserImage userImage = new UserImage();
					userImage.setCamera_make((String)event.get("camera_make"));
					userImage.setCamera_model((String)event.get("camera_model"));
					userImage.setEvent_time((String)event.get("event_time"));
					userImage.setKey((String)event.get("key"));
					
					String customerId = (String)event.get("customer_id");
					Customer customer = customerMap.get(customerId);
					customer.addImage(userImage);
					
				} else{
					System.out.println("Unrecognized Verb");
				}
				
				break;
			
				/**
				 * The code is executed when the event type is order
				 *  When the event verb is new, a new order is added to the container 
				 *  for that particular customer
				 *  When the event verb is update, the existing order details in the 
				 *  data container are replaced with the updated values.
				 */
			case "ORDER":
				
				if(eventVerb.equals("NEW") || eventVerb.equals("UPDATE")){	
					Order order = new Order();
					order.setEvent_time((String)event.get("event_time"));
					order.setTotal_amount(Float.parseFloat((String) event.get("total_amount")));
					order.setKey((String)event.get("key"));
					
					String customerId = (String)event.get("customer_id");
					Customer customer = customerMap.get(customerId);
					
					if(eventVerb.equals("UPDATE")){
						customer.updateOrder(order);
					} else{
						customer.addOrder(order);
					}
				} else{
					System.out.println("Unrecognized Verb");
				}
				
				break;
			
			default:
				System.out.println("Please enter a valid event type");
		}
		
		 	
	}
	
	/**
	 * Method to read the file from the input path which is in a JSON format
	 * The entire file is a JSONArray
	 * Each line in the file is a JSONObject
	 * Each JSONObject contains key value pairs
	 * @param String - Path of the input file.
	 * @return input data 
	 */
	public static JSONArray readInputFile(String path){
		JSONParser parser = new JSONParser();
		JSONArray inputData = null;
		try {
			inputData = (JSONArray) parser.parse(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return inputData;
	}
}
