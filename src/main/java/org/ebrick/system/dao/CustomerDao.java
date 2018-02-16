package org.ebrick.system.dao;

import java.util.List;

import org.ebrick.system.model.Customer;

public interface CustomerDao {
	
	public List<Customer> getCustomers() ;

	public Customer getCustomer(int customerId);

	public Customer addCustomer(Customer customerId);

	public Customer updateCustomer(Customer customerId);

	public void deleteCustomer(int customerId);

}
