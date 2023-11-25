package com.spring.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@TableName("role")
@NoArgsConstructor // needed by "select"
@AllArgsConstructor // needed by @Builder
public class Role implements Serializable {
    @TableId(type = IdType.AUTO)
    private int id;
    private String roleType;
    private String roleDescrip;




}
