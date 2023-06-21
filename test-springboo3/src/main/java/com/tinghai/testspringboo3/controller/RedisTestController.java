package com.tinghai.testspringboo3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author chendoudou
 * @description redis相关demo
 * @date 2023/6/2 10:51
 **/
@RestController
public class RedisTestController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * redis分布式锁
     */
    public void test() {
        String lockKey = "test";
        String lockValue = UUID.randomUUID().toString().replaceAll("-", "") + Thread.currentThread().getId();
        Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue,30, TimeUnit.SECONDS);
        try {
            if (result) {
                //加锁成功
                // TODO 执行业务代码
            }else {
                //根据业务判断是否需要重新抢锁(可以加个Thread.sleep(50),不然竞争锁太过激烈）或者或者直接放弃抢锁
                //test()
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //释放锁
            releaseLock(lockKey,lockValue);
        }
    }

    private void releaseLock(String lockKey, String lockValue) {
        String script = "if redis.call('get',KEYS[1]) == ARGV[1]\n" +
                "then\n" +
                "return redis.call('del',KEYS[1])\n" +
                "else\n" +
                "   return 0\n" +
                "end";
        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setResultType(Long.class);
        defaultRedisScript.setScriptText(script);
        redisTemplate.execute(defaultRedisScript, Arrays.asList(lockKey), lockValue);
    }

}
