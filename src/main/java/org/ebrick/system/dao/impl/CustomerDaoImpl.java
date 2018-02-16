package org.ebrick.system.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ebrick.system.dao.CustomerDao;
import org.ebrick.system.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl implements CustomerDao  {
		
		  private static final Map<Integer,Customer> customerMap =new HashMap<Integer,Customer>();
		  
		    static {
		        initializeCustomers();
		    }
		 
		    public Customer getCustomer(int customerId) {
		        return customerMap.get(customerId);
		    }
		 
		    public Customer addCustomer(Customer customer) {
		        customerMap.put(customer.getId(), customer);
		        return customer;
		    }
		 
		    public Customer updateCustomer(Customer customer) {
		        customerMap.put(customer.getId(), customer);
		        return customer;
		    }
		 
		    public void deleteCustomer(int customerId) {
		        customerMap.remove(customerId);
		    }
		 
		    
		    private static void initializeCustomers() {
		    	Customer c = new Customer();
				c.setId(1);
				c.setName("Apple");
				c.setAddress("2/44");
				c.setCity("Hyderabad");
				c.setState("Telangana");
				c.setPincode("500445");
				c.setCountry("India");
		 
		        customerMap.put(c.getId(), c);
		    }
		    

			@Override
			public List<Customer> getCustomers() {
				 Collection<Customer> c = customerMap.values();
			        List<Customer> list = new ArrayList<Customer>();
			        list.addAll(c);
			        return list;				
			}


}
