package com.tinghai.testspringboo3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chendoudou
 * @description
 * @date 2023/6/2 10:51
 **/
@RestController
@Tag(name = "教师模块")
@RequestMapping("/teacher")
public class TeacherController {

    @Operation(summary = "你好")
    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "Hello World!";
    }

}
