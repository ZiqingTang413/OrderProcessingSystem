package com.spring.shopping;

import com.spring.shopping.entity.Order;
import com.spring.shopping.entity.User;
import com.spring.shopping.mapper.OrderMapper;
import com.spring.shopping.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootTest
class ShoppingApplicationTests {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private OrderMapper orderMapper;
	@Test
	public void testSelectOrder() throws IOException, ParseException {

		List<Order> orderList = orderMapper.selectOrder(
				"i"
				, "s"
				,dateFormat.parse("2020-01-01 00:00:00")
				, dateFormat.parse("2020-12-31 23:59:59"));

		System.out.println(orderList);
	}

	@Test
	public void testAddOrder() throws IOException, ParseException {
		Order newOrder = new Order();
		newOrder.setOrderPrice(10.5);
		newOrder.setOrderTime(dateFormat.parse("2023-11-11 00:10:15"));
		newOrder.setUid(1);
		newOrder.setQuantity(1);
		newOrder.setCommodity("pencil");

		orderMapper.addOrder(newOrder);
		System.out.println("insert succeed!");


	}


	@Test
	public void testDeleteOrderById() throws IOException{
		int id = 11;
		if (orderMapper.deleteOrderById(id) == 1){
		System.out.println("Delete succeed!");}
	}
	@Test
	public void testDeleteOrdersByIds() throws IOException{
		int[] ids = {8, 9};
		int numUpdate = orderMapper.deleteOrdersByIds(ids);
		if (numUpdate == ids.length) {
			System.out.println("Delete " + numUpdate + " items");
		}
	}

	@Test
	public void testUpdateOrderById() throws IOException, ParseException {
		Order newOrder = new Order();
		newOrder.setOrderTime(dateFormat.parse("2023-11-11 10:10:10"));
		newOrder.setCommodity("television");
		newOrder.setId(6);
		System.out.println(newOrder.toString());
		if (orderMapper.updateOrderById(newOrder) == 1) {
			System.out.println("update succeed!");
		}
	}
	/**----------------------------------*/
	@Autowired
	UserMapper userMapper;
	@Test
	public void testSelectUserAndRole() throws IOException{
		String username = "Lucy";
		String roleType = "user";
		List<User> userList1 = userMapper.selectUserAndRole(username, null);
		for(User user:userList1) {
			System.out.println(user);
		}
		List<User> userList2 = userMapper.selectUserAndRole(null, roleType);
		for(User user:userList2) {
			System.out.println(user);
		}
		List<User> userList3 = userMapper.selectUserAndRole(username, roleType);
		for(User user:userList3) {
			System.out.println(user);
		}

	}

}
