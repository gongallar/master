package org.ebrick.system.controller;

import java.util.List;

import org.ebrick.system.dao.CustomerDao;
import org.ebrick.system.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	@Autowired
	private CustomerDao customerDAO;

	@ResponseBody
	@RequestMapping(value = "/getCustomers", method = RequestMethod.GET)
	public List<Customer> getCustomers() {
		List<Customer> customers = customerDAO.getCustomers();
		return customers;
	}

	@RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
	public Customer getCustomer(@PathVariable("customerId") int customerId) {
		return customerDAO.getCustomer(customerId);
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	@ResponseBody
	public Customer addCustomer(@RequestBody Customer customerId) {

		System.out.println(" Creating Customer: " + customerId.getId());

		return customerDAO.addCustomer(customerId);
	}

	@ResponseBody
	@RequestMapping(value = "/customer", method = RequestMethod.PUT)
	public Customer updateCustomer(@RequestBody Customer customer) {

		System.out.println(" Editing Customer: " + customer.getId());

		return customerDAO.updateCustomer(customer);
	}
    @ResponseBody
	@RequestMapping(value = "/customer/{customerId}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable("customerId") int customerId) {
 
        System.out.println(" Deleting Customer: " + customerId);
 
        customerDAO.deleteCustomer(customerId);
    }
}
