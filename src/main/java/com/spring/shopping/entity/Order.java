package com.spring.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Order {


        @TableId(type = IdType.AUTO)
        private int id;
        private Date orderTime;
        private double orderPrice = -1;
        private String commodity;
        private int quantity = -1;
        private int uid = -1;

        private User user; // one order is only related to one user

        @Override
        public String toString() {
            return "Order{" +
                    "id=" + id +
                    ", orderTime='" + orderTime + '\'' +
                    ", orderPrice=" + orderPrice +
                    ", uid=" + uid +

                    '}';
        }
    }

