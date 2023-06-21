package com.tinghai.testspringboo3.controller;

import com.tinghai.testspringboo3.entity.Student;
import com.tinghai.testspringboo3.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author chendoudou
 * @description
 * @date 2023/6/2 10:51
 **/
@RestController

@Tag(name = "学生模块")
@RequestMapping("/test")
public class TestController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "列表")
    @GetMapping("/findAllStudent")
    public List<Student> findAllStudent(@Parameter( description = "学生主键id") Integer id) {
        return studentService.getStudentById(id);
    }

}
