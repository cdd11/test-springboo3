package com.tinghai.testspringboo3.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tinghai.testspringboo3.entity.Student;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author chendoudou
 * @description
 * @date 2023/6/2 11:53
 **/
@Repository
public interface StudentMapper extends BaseMapper<Student> {
    void batchInsert(List<Student> studentList);
}
