package org.ebrick.system.dao;

import java.util.List;

import org.ebrick.system.model.Order;

public interface OrderDao {
	
	public List<Order> getOrders() ;

	public Order getOrder(int orderId);

	public Order addOrder(Order orderId);

	public Order updateOrder(Order orderId);

	public void deleteOrder(int orderId);

}
