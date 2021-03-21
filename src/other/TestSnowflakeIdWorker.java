package other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.ProxyLoader;

public class TestSnowflakeIdWorker {

    int testNum = 100000; //100w的数据量
    SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);//订单生成中心
    ExecutorService pool = Executors.newFixedThreadPool(50);//线程池
    HashSet set = new HashSet();    //Set的add()非线程安全方法

    @org.junit.Test //单元测试注解
    public void testIncrease() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(testNum);//控制线程通信
        while (testNum-- > 0) { //循环100w次
            pool.submit(() -> { //线程池提交任务
                synchronized (set) {    //保证线程安全
                    assert set.add(idWorker.nextId());  //若订单号出现重复,Set.add()将返回false,断言将抛出AssertionError
                }
                countDownLatch.countDown(); //闭锁自减,当减为0时唤醒主线程
            });
        }
        countDownLatch.await(); //主线程阻塞

        System.out.println(set.size()); //此处应为100w
    }
}