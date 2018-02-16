package org.ebrick.system;

import java.util.ArrayList;
import java.util.List;

import org.ebrick.system.model.Brick;
import org.ebrick.system.model.Customer;
import org.ebrick.system.model.Order;
import org.ebrick.system.model.Organisation;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;


public class OrderTest {

    private RestTemplate restTemplate = new RestTemplate();
    
    @Test
    public void getOrderByOrderIdTest() throws JsonProcessingException,  org.json.simple.parser.ParseException {
    	System.out.println("-------------Get orders----By Orderid-----------------");
    	Order order = new Order();
    	order.setOrderId(5001);
    	HttpHeaders requestHeaders = new HttpHeaders();
    	requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    	//HttpEntity<String> httpEntity = new HttpEntity<String>(asJsonString(order), requestHeaders);
    	ResponseEntity<Order> apiResponse = restTemplate.getForEntity("http://localhost:8082/order/5001", Order.class);
    	Order orderDetails = apiResponse.getBody();
    	System.out.println(orderDetails);
    	Assert.assertEquals(5001, orderDetails.getOrderId());
	 }
    
    
    @Test
    public void getOrderByNoOrderIdTest() throws JsonProcessingException,  org.json.simple.parser.ParseException {
    	System.out.println("------------getOrderByNoOrderIdTest()----------------");
    	Order order = new Order();
    	order.setOrderId(5001);
    	HttpHeaders requestHeaders = new HttpHeaders();
    	requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    	//HttpEntity<String> httpEntity = new HttpEntity<String>(asJsonString(order), requestHeaders);
    	ResponseEntity<Order> apiResponse = restTemplate.getForEntity("http://localhost:8082/order/500", Order.class);
    	Order orderDetails = apiResponse.getBody();
    	System.out.println(orderDetails);
    	Assert.assertEquals("No Order Details Found", orderDetails.getStatus());
    	Assert.assertNotEquals(500, orderDetails.getOrderId());

    	
	 }
    
    @Test
    public void getOrdersTest() throws JsonProcessingException,  org.json.simple.parser.ParseException {
    	System.out.println("-------------Get orders--------------------");
    	Order order = new Order();
    	order.setOrderId(5001);
    	HttpHeaders requestHeaders = new HttpHeaders();
    	requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    	ResponseEntity<Order[]> apiResponse = restTemplate.getForEntity("http://localhost:8082/getOrders", Order[].class);
    	for(Order o:apiResponse.getBody())
    	{
    	 System.out.println(o);
    	}
    	Assert.assertNotNull(apiResponse.getBody());
	 }
    
    @Test
    public void createOrdersForCustomersTest() throws JsonProcessingException,  org.json.simple.parser.ParseException {
    	System.out.println("------------createOrdersForCustomers--------------------");
    	Order order = new Order();
    	
    	List<Customer> customers =new ArrayList<Customer>();
    	Customer c = new Customer();
    	c.setId(1);
		customers.add(c);
		
		Brick b=new Brick();
		b.setId(101);
		Organisation org=new Organisation();
		org.setId(1000);
		
		order.setBrick(b);
		order.setQty(2);
		order.setCustomers(customers); 
		order.setOrganisation(org);
		
    	HttpHeaders requestHeaders = new HttpHeaders();
    	requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    	ResponseEntity<Order[]> apiResponse = restTemplate.postForEntity("http://localhost:8082/createOrdersforCustomers",order,Order[].class);
    	for(Order o:apiResponse.getBody())
    	{
    	  System.out.println(o);
    	}
    	Assert.assertNotNull(apiResponse.getBody());
	 	
	 }
    
    @Test
    public void getCustomersTest() throws JsonProcessingException,  org.json.simple.parser.ParseException {
    	System.out.println("-------------Get Customers--------------------");
    	Order order = new Order();
    	order.setOrderId(5001);
    	HttpHeaders requestHeaders = new HttpHeaders();
    	requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    	ResponseEntity<Customer[]> apiResponse = restTemplate.getForEntity("http://localhost:8082/getCustomers",Customer[].class);
    	Customer[] customers = apiResponse.getBody();
    	System.out.println(customers);
    	for(Customer c:customers)
    	{
    	 System.out.println(c);
    	}
    	Assert.assertNotNull(apiResponse.getBody());
    	}
    
    @Test
    public void getBricksTest() throws JsonProcessingException,  org.json.simple.parser.ParseException {
    	System.out.println("-------------Get orders--------------------");
    	Order order = new Order();
    	order.setOrderId(5001);
    	HttpHeaders requestHeaders = new HttpHeaders();
    	requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    	ResponseEntity<Brick[]> apiResponse = restTemplate.getForEntity("http://localhost:8082/getBricks",Brick[].class);
    	Brick[] bricks = apiResponse.getBody();
    	for(Brick b:bricks)
    	{
    	 System.out.println(b);
    	}
    	Assert.assertEquals(2, bricks.length);
    	
	 }
    
    
    
}
