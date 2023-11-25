package com.spring.shopping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.spring.shopping.entity.Role;
import com.spring.shopping.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.spring.shopping.util.FastJsonRedisSerializer.DEFAULT_CHARSET;

@SpringBootTest
public class FastJsonTest {

    @Test
    void testObject2JSON() {
        Date birthday = new Date();
        Role role = new Role(2,"user","general user");

        var user = User.builder()
                .id(10)
                .username("Andy")
                .birthday(birthday)
                .password("0000") // save the crypted password
                .roleList(new ArrayList<>(Arrays.asList("user")))
                .build();


        byte[] userByte = JSON.toJSONString(user, SerializerFeature.WriteClassName, SerializerFeature.WriteDateUseDateFormat)
                .getBytes(DEFAULT_CHARSET);


        String str = new String(userByte, DEFAULT_CHARSET);
        System.out.println(JSON.parseObject(str,User.class));


    }
}
