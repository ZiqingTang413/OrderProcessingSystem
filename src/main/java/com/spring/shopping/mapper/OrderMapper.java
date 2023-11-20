package com.spring.shopping.mapper;

import com.spring.shopping.entity.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface OrderMapper {
    // public List<Order> selectOrderByUser(String username);

    public List<Order> selectOrder(String username, String product, Date from, Date to);

   @Insert("insert into orders (order_time, order_price, commodity, quantity, uid) values (#{orderTime}, #{orderPrice}, #{commodity}, #{quantity}, #{uid})")
    public void addOrder(Order order);

    public int updateOrderById(Order order);
    @Delete("delete from orders where id = #{id}")
    public int deleteOrderById(int id);

    public int deleteOrdersByIds(@Param("ids")int[] ids);
}
