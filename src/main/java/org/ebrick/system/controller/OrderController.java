package org.ebrick.system.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ebrick.system.dao.BrickDao;
import org.ebrick.system.dao.CustomerDao;
import org.ebrick.system.dao.OrderDao;
import org.ebrick.system.dao.OrganisationDao;
import org.ebrick.system.model.Brick;
import org.ebrick.system.model.Customer;
import org.ebrick.system.model.Order;
import org.ebrick.system.model.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	@Autowired
	private OrderDao orderDAO;

	@Autowired
	private BrickDao brickDAO;

	@Autowired
	private CustomerDao customerDAO;

	@Autowired
	private OrganisationDao orgDAO;

	@ResponseBody
	@RequestMapping(value = "/createOrders", method = RequestMethod.POST)
	public Order createOrder(@RequestBody Order orderId) {

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		String currentDate = format.format(date);
		int bId = orderId.getBrick().getId();
		int oid = orderId.getOrganisation().getId();
		int qty = orderId.getQty();

		Organisation org = orgDAO.getOrganisation(oid);

		Brick b = brickDAO.getBrick(bId);
		double price = b.getPrice() * qty;

		List<Brick> bricks = new ArrayList<Brick>();
		bricks.add(b);

		List<Customer> customers = new ArrayList<Customer>();
		for (Customer c : orderId.getCustomers()) {
			int customerId = c.getId();
			Customer customer = customerDAO.getCustomer(customerId);
			if (customer == null) {
				customer = new Customer();
				customer.setId(customerId);
				customer.setName("mango");
				customer.setAddress("66/44");
				customer.setCity("Hyderabad");
				customer.setState("Telangana");
				customer.setPincode("600445");
				customer.setCountry("India");

			}
			customerDAO.addCustomer(customer);
			customers.add(customer);
		}

		Order o = new Order();
		o.setOrderId(20180217);
		o.setOrganisation(org);
		o.setBrick(b);
		o.setCustomers(customers);
		o.setQty(qty);
		o.setPrice(price);
		o.setDate(currentDate);
		o.setStatus("dispatched");

		Order order = orderDAO.addOrder(o);
		if (order.getOrderId() != 0) {
			int balQty = b.getTotQty() - qty;
			b.setQty(qty);
			b.setBalQty(balQty);
			;
			brickDAO.updateBrick(b);
		}
		return order;
	}

	@ResponseBody
	@RequestMapping(value = "/createOrdersforCustomers", method = RequestMethod.POST)
	public List<Order> createOrdersforCustomers(@RequestBody Order orderId) {

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		String currentDate = format.format(date);
		int bId = orderId.getBrick().getId();
		int oid = orderId.getOrganisation().getId();
		int qty = orderId.getQty();
		
		List<Customer> customers = null;
		List<Order> orders =new ArrayList<Order>();
		List<Brick> bricks = new ArrayList<Brick>();
		
		Organisation org = orgDAO.getOrganisation(oid);

		Brick b = brickDAO.getBrick(bId);
		double price = b.getPrice() * qty;
	
		bricks.add(b);

		for (Customer c : orderId.getCustomers()) {
			customers = new ArrayList<Customer>();
			int customerId = c.getId();
		
			Customer customer = customerDAO.getCustomer(customerId);
			
			if (customer == null) {
				customer = new Customer();
				customer.setId(customerId);
				customer.setName("mango");
				customer.setAddress("66/44");
				customer.setCity("Hyderabad");
				customer.setState("Telangana");
				customer.setPincode("600445");
				customer.setCountry("India");
	
			}
			customers.add(customer);

			customerDAO.addCustomer(customer);
			
			Order o = null;
			o = new Order();
			int i = 201821;
			o.setOrderId(i + 1);
			o.setOrganisation(org);
			o.setBrick(b);
			o.setCustomers(customers);
			o.setQty(qty);
			o.setPrice(price);
			o.setDate(currentDate);
			o.setStatus("dispatched");

			Order order = orderDAO.addOrder(o);
			if (order.getOrderId() != 0) {
				int balQty = b.getBalQty() - qty;
				b.setQty(qty);
				b.setBalQty(balQty);
				brickDAO.updateBrick(b);
			}
			  order = orderDAO.updateOrder(o);
			
			orders.add(order);
		}

		return orders ;
	}

	@ResponseBody
	@RequestMapping(value = "/getOrders", method = RequestMethod.GET)
	public List<Order> getOrders() {
		List<Order> orders = orderDAO.getOrders();
		return orders;
	}

	@RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
	public Order getOrder(@PathVariable("orderId") int orderId) {
		if (orderDAO.getOrder(orderId) != null)
			return orderDAO.getOrder(orderId);
		else {
			Order o = new Order();
			o.setStatus("No Order Details Found");
			return o;
		}
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	@ResponseBody
	public Order addOrder(@RequestBody Order orderId) {

		System.out.println(" Creating Order: " + orderId.getOrderId());

		return orderDAO.addOrder(orderId);
	}

	@ResponseBody
	@RequestMapping(value = "/order", method = RequestMethod.PUT)
	public Order updateOrder(@RequestBody Order order) {

		System.out.println(" Editing Order: " + order.getOrderId());
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		String currentDate = format.format(date);
		
		
		Order o1 = new Order();
				o1=orderDAO.getOrder(201822);
		
		System.out.println();
		
		Brick b =new Brick();
		if (o1 != null) {
			b=brickDAO.getBrick(102);
			b.setQty(order.getQty());
			b.setBalQty(b.getBalQty()-b.getQty());
			brickDAO.updateBrick(b);
			o1.setBrick(b);
			o1.setQty(order.getQty());
			o1.setPrice(b.getPrice()*b.getQty());
			o1.setDate(currentDate);
			o1=orderDAO.updateOrder(o1);
			
		}

		return o1;
	}

	@ResponseBody
	@RequestMapping(value = "/order/{orderId}", method = RequestMethod.DELETE)
	public void deleteOrder(@PathVariable("orderId") int orderId) {

		System.out.println(" Deleting Order: " + orderId);

		orderDAO.deleteOrder(orderId);
	}
}
