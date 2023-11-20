package com.spring.shopping.mapper;


import com.spring.shopping.entity.User;

import java.util.List;

public interface UserMapper {
    public List<User> selectUserAndRole(String username, String roleType);
}
