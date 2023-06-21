package com.tinghai.testspringboo3;

import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import com.tinghai.testspringboo3.dao.StudentMapper;
import com.tinghai.testspringboo3.entity.Student;
import com.tinghai.testspringboo3.service.StudentService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TestSpringboo3ApplicationTests {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StudentService studentService;
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    private DataSource dataSource; // 注入数据源对象

    @Test
    void contextLoads() {
        List<Student> studentList = studentService.getStudentById(11);
    }

    @Test
    void testOne() {
        redisTemplate.opsForValue().set("name", "卷心菜");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name); //卷心菜
    }


    @Test
    void testA() {
//		21 mybatis
//		37367 mybatis-plus 自带
//		69 jdbc
//        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            long l = System.currentTimeMillis();
            int batchSize = 1000; // 批量提交的数据量
            int totalCount = 1000000; // 数据总量
            for (int i = 0; i < totalCount; i += batchSize) {
                // 3. 构造一个List来保存本次要插入的数据
                List<Student> studentList = new ArrayList<>();
                for (int j = i; j < i + batchSize && j < totalCount; j++) {
                    Student student = new Student();
                    student.setName("User " + j);
                    student.setAge(1);
                    studentList.add(student);
                }
                // 4. 调用Mapper的批量插入方法，将本次要插入的数据提交到数据库中

                studentMapper.batchInsert(studentList);

                // 5. 手动提交
//                sqlSession.commit();
            }
            System.out.println("数据插入成功:耗时" + (System.currentTimeMillis() - l));
        } catch (Exception e) {
            e.printStackTrace();
//            sqlSession.rollback();
        } finally {
            // 7. 释放资源
//            sqlSession.close();
        }
    }


    @Test
    void testB() throws SQLException {
        long l = System.currentTimeMillis();
// 使用JDBC进行批量插入
        // 获取JDBC连接
        Connection conn = DataSourceUtils.getConnection(dataSource);
        conn.setAutoCommit(false);
        // 2. 插入数据
        int batchSize = 1000;
        int totalCount = 1000000;
        int count = 0;
        String sql = "INSERT INTO student(name, age) VALUES(?, ?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            // 3. 逐批次插入数据
            for (int i = 0; i < totalCount; i++) {
                Student student = new Student();
                student.setName("User " + i);
                student.setAge(1);
                pstmt.setString(1, student.getName());
                pstmt.setInt(2, student.getAge());
                pstmt.addBatch();
                count++;
                if (count % batchSize == 0) {
                    // 4. 批量提交
                    pstmt.executeBatch();
                    conn.commit();
                    pstmt.clearBatch();
                }
            }
            pstmt.executeBatch();
            conn.commit();
            pstmt.clearBatch();
            System.out.println("数据插入成功:耗时" + (System.currentTimeMillis() - l));
        } finally {
            // 5. 释放资源
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
