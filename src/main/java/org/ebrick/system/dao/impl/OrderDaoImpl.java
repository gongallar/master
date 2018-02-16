package org.ebrick.system.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ebrick.system.dao.OrderDao;
import org.ebrick.system.model.Brick;
import org.ebrick.system.model.Customer;
import org.ebrick.system.model.Order;
import org.ebrick.system.model.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {

	private static final Map<Integer, Order> orderMap = new HashMap<Integer, Order>();

	static {
		initializeOrders();
	}

	public Order getOrder(int oid) {
		return orderMap.get(oid);
	}

	public Order addOrder(Order order) {
		orderMap.put(order.getOrderId(), order);
		return order;
	}

	public Order updateOrder(Order order) {
		orderMap.put(order.getOrderId(), order);
		return order;
	}

	public void deleteOrder(int orderId) {
		orderMap.remove(orderId);
	}

	private static void initializeOrders() {
		Order order1 = new Order();

		Customer c = new Customer();
		c.setId(1);
		c.setName("Apple");
		c.setAddress("2/4");
		c.setCity("Hyderabad");
		c.setState("Telangana");
		c.setPincode("500486");
		c.setCountry("India");

		List<Customer> customers = new ArrayList<Customer>();
		customers.add(c);
		
		order1.setOrderId(5001);
		order1.setPrice(20);
		order1.setQty(2);

		Brick b=new Brick();
		b.setId(101);
		b.setQty(2);
		
		List<Brick> bricks = new ArrayList<Brick>();
		bricks.add(b);
		
		OrganisationDaoImpl orgDAO= new OrganisationDaoImpl();
		Organisation org=orgDAO.getOrganisation(1000);
		
		order1.setOrganisation(org);
		order1.setBrick(b);
		order1.setCustomers(customers);
		order1.setStatus("dispatched");
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		String currentDate = format.format(date);
		order1.setDate(currentDate);

		orderMap.put(order1.getOrderId(), order1);
	}

	public List<Order> getOrders() {
		Collection<Order> c = orderMap.values();
		List<Order> list = new ArrayList<Order>();
		list.addAll(c);
		return list;
	}

}
