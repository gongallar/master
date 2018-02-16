package org.ebrick.system;

import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ebrick.system.dao.BrickDao;
import org.ebrick.system.dao.CustomerDao;
import org.ebrick.system.dao.OrderDao;
import org.ebrick.system.dao.OrganisationDao;
import org.ebrick.system.dao.impl.OrderDaoImpl;
import org.ebrick.system.model.Brick;
import org.ebrick.system.model.Customer;
import org.ebrick.system.model.Order;
import org.ebrick.system.model.Organisation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class TestOrder {

	private static OrganisationDao orgDAO;
	private static BrickDao brickDAO;
	private static CustomerDao customerDAO;
	private static OrderDao orderDAO;
	private static String currentDate;

	@BeforeClass
	public static void setUp() {
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		currentDate = format.format(date);


		orgDAO = Mockito.mock(OrganisationDao.class);
		brickDAO = Mockito.mock(BrickDao.class);
		customerDAO = Mockito.mock(CustomerDao.class);
		orderDAO = Mockito.mock(OrderDaoImpl.class);

		Organisation o = new Organisation();
		o.setId(1000);
		o.setName("DRDL");
		o.setAddress("2/24");
		o.setCity("Hyderabad");
		o.setState("Telangana");
		o.setPincode("500486");
		o.setCountry("India");

		Brick brick1 = new Brick(101, "clay", 10, 0, 50, 50);
		Brick brick2 = new Brick(102, "cement", 20, 0, 50, 50);

		Customer c = new Customer();
		c.setId(1);
		c.setName("Krishna");
		c.setAddress("2/44");
		c.setCity("Hyderabad");
		c.setState("Telangana");
		c.setPincode("500445");
		c.setCountry("India");

		when(orgDAO.getOrganisation(Mockito.anyInt())).thenReturn(o);
		when(brickDAO.getBrick(101)).thenReturn(brick1);
		when(brickDAO.getBrick(102)).thenReturn(brick2);
		when(customerDAO.getCustomer(Mockito.anyInt())).thenReturn(c);

	}

	/*
	 * I want to submit new orders for bricks So I can start customers’ orders
	 */
	@Test
	public void createOrders() {

		Order orderId = new Order();
		orderId.setQty(2);
		Brick b1 = new Brick();
		b1.setId(101);
		orderId.setBrick(b1);

		Customer customer1 = new Customer();
		customer1.setId(1);

		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customer1);

		orderId.setCustomers(customers);

		Organisation organisation = new Organisation();
		orderId.setOrderId(1000);
		orderId.setOrganisation(organisation);

		int bId = orderId.getBrick().getId();
		int customerId = orderId.getCustomers().get(0).getId();
		int oid = orderId.getOrganisation().getId();
		int qty = orderId.getQty();

		Organisation org = orgDAO.getOrganisation(oid);

		Brick b = brickDAO.getBrick(bId);

		double price = b.getPrice() * qty;

		List<Brick> bricks = new ArrayList<Brick>();
		bricks.add(b);

		System.out.println(b.getType());

		Customer customer2 = customerDAO.getCustomer(customerId);

		List<Customer> customers2 = new ArrayList<Customer>();
		customers2.add(customer2);

		Order o = new Order();
		o.setOrderId(20180217);
		o.setOrganisation(org);
		o.setBrick(b);
		o.setCustomers(customers);
		o.setQty(qty);
		o.setPrice(price);
		o.setDate(currentDate);
		o.setStatus("dispatched");

		when(orderDAO.addOrder(Mockito.anyObject())).thenReturn(o);

		Order order = orderDAO.addOrder(o);

		System.out.println("Order Date:" + order.getDate());

		System.out.println("order:" + order.getOrderId());

		if (order.getOrderId() != 0) {
			int balQty = b.getTotQty() - qty;
			b.setQty(qty);
			b.setBalQty(balQty);
			brickDAO.updateBrick(b);
		}

		System.out.println("Order Reference:" + order.getOrderId());
		System.out.println("Order Qty:" + order.getQty());

		Assert.assertEquals(20180217, order.getOrderId());
		Assert.assertEquals(2, order.getQty());
		Assert.assertNotNull(order.getBrick());
		Assert.assertNotNull(order.getCustomers());
		Assert.assertNotNull(order.getOrganisation());

	}

	/*
	 * I want to retrieve orders So I can display simple customers’ orders
	 */
	@Test
	public void NoOrders() {

		Order order = new Order();
		order.setOrderId(20180218);

		if (orderDAO.getOrder(order.getOrderId()) == null) {
			order.setStatus("No Order Details Found");
		}

		Assert.assertNull(order.getBrick());
		Assert.assertEquals("No Order Details Found", order.getStatus());

	}

	@Test
	public void createOrdersforCustomers() {

	
		Order orderId = new Order();
		orderId.setQty(2);
		Brick b1 = new Brick();
		b1.setId(101);
		orderId.setBrick(b1);

		Customer customer1 = new Customer();
		customer1.setId(1);

		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customer1);

		orderId.setCustomers(customers);

		Organisation organisation = new Organisation();
		orderId.setOrderId(1000);
		orderId.setOrganisation(organisation);

		int bId = orderId.getBrick().getId();
		int customerId = 0;
		for (Customer c : orderId.getCustomers())
			customerId = c.getId();
		int oid = orderId.getOrganisation().getId();
		int qty = orderId.getQty();

		Organisation org = orgDAO.getOrganisation(oid);

		Brick b = brickDAO.getBrick(bId);

		double price = b.getPrice() * qty;

		List<Brick> bricks = new ArrayList<Brick>();
		bricks.add(b);

		System.out.println(b.getType());

		Customer customer = customerDAO.getCustomer(customerId);

		Customer customer2 = new Customer();
		customer2.setId(1);
		customer2.setName("Grapes");
		customer2.setAddress("4/45");
		customer2.setCity("Hyderabad");
		customer2.setState("Telangana");
		customer2.setPincode("500456");
		customer2.setCountry("India");

		List<Customer> customers2 = new ArrayList<Customer>();
		customers2.add(customer);
		customers2.add(customer2);

		Order o = new Order();
		o.setOrderId(20180218);
		o.setOrganisation(org);
		o.setBrick(b);
		o.setQty(qty);
		o.setPrice(price);
		o.setDate(currentDate);
		o.setCustomers(customers2);
		o.setStatus("dispatched");

		when(orderDAO.addOrder(Mockito.anyObject())).thenReturn(o);

		Order order = orderDAO.addOrder(o);

		System.out.println("Order Date:" + order.getDate());

		System.out.println("order:" + order.getOrderId());

		if (order.getOrderId() != 0) {
			int balQty = b.getTotQty() - qty;
			b.setQty(qty);
			b.setBalQty(balQty);
			brickDAO.updateBrick(b);
		}

		System.out.println("Order Reference:" + order.getOrderId());
		System.out.println("Order Qty:" + order.getQty());

		Assert.assertEquals(20180218, order.getOrderId());
		Assert.assertEquals(2, order.getQty());
		Assert.assertNotNull(order.getBrick());
		Assert.assertNotNull(order.getCustomers());
		Assert.assertNotNull(order.getOrganisation());
		Assert.assertEquals(2, order.getCustomers().size());

	}

	@Test
	public void updateOrdersforCustomers() {
	 
		Organisation org = orgDAO.getOrganisation(1000);

		
		Customer customer = customerDAO.getCustomer(1);
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customer);
		
		Order o=new Order();
		o.setOrganisation(org);
		o.setCustomers(customers);
		o.setOrderId(20180218);
		
		when(orderDAO.getOrder(Mockito.anyInt())).thenReturn(o);

		Order o1 = orderDAO.getOrder(20180218);
		Order o2=new Order();
		o2.setOrganisation(org);
		o2.setCustomers(customers);
		o2.setOrderId(20180218);
		
		Brick b =new Brick();
		if (o1 != null) {
			b=brickDAO.getBrick(102);
			b.setQty(2);
			b.setBalQty(b.getBalQty()-b.getQty());
			brickDAO.updateBrick(b);
			o2.setBrick(b);
			o2.setQty(2);
			o2.setPrice(b.getPrice()*b.getQty());
			o2.setDate(currentDate);
			orderDAO.updateOrder(o1);
			
		}
		
		Assert.assertEquals(20180218, o2.getOrderId());
		Assert.assertEquals(102, b.getId());
		Assert.assertEquals("cement", b.getType());
		Assert.assertEquals(2, b.getQty());

	}
	
 

}
