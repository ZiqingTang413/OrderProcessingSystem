package com.spring.shopping.mapper;


import com.spring.shopping.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

public interface UserMapper {
    public List<User> selectUserAndRole(String username, String roleType);

    public Optional<User> selectUserByUsername(String username);

    @Insert("insert into user (username, password, birthday) values (#{username}, #{password}, #{birthday})")
    public void saveUser(User user);
    @Insert("insert into user_role (user_id, role_id) values (#{userId}, #{roleId})")
    public void saveUserRole(int userId, int roleId);

    public User selectUserRoleByUsername(String username);
}
