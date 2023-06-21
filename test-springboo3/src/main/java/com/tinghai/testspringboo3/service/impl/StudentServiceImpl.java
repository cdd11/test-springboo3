package com.tinghai.testspringboo3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tinghai.testspringboo3.dao.StudentMapper;
import com.tinghai.testspringboo3.entity.Student;
import com.tinghai.testspringboo3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author chendoudou
 * @description
 * @date 2023/6/2 11:59
 **/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getStudentById(Integer id) {
        List<Student> studentList = this.lambdaQuery().eq(id != null, Student::getId, id).list();
        return studentList;
    }
}
