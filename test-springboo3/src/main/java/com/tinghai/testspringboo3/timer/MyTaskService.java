package com.tinghai.testspringboo3.timer;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author chendoudou
 * @description
 * @date 2023/6/27 14:34
 **/
@Service
public class MyTaskService {

    @Scheduled(fixedRate = 1000)
    @Async
    public void myAsyncTask() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM HH:mm:ss");
        String format = LocalDateTime.now().format(formatter);
        System.out.println("开始执行异步任务，线程名称：" + Thread.currentThread().getName()+"  当前时间+"+format);
        Thread.sleep(5000);
        String format2 = LocalDateTime.now().format(formatter);
        System.out.println("异步任务执行完成，线程名称：" + Thread.currentThread().getName()+"  当前时间+"+format2);
    }
}
