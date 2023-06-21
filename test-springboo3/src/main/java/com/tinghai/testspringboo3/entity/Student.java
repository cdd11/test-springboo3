package com.tinghai.testspringboo3.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author chendoudou
 * @description
 * @date 2023/6/2 11:51
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("student")
public class Student {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
}
