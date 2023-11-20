package com.spring.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private int id;
    private String username;
    private String password;
    private Date birthday;

    private List<Role> roleList;

}
