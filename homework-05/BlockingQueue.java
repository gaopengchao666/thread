package com.gupaoedu.simplequeue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列
 * @author gaopengchao
 * 2019年5月22日
 */
public class BlockingQueue
{
    private final int CAPACITY = 16;//容器大小
    
    private Queue<Integer> queue = new ArrayDeque<>(CAPACITY); //容器
    
    private static final Lock lock = new ReentrantLock();

    private static final Condition notEmptyCondition = lock.newCondition();

    private static final Condition notFullCondition = lock.newCondition();
    
    public void put(Integer i) throws InterruptedException
    {
        lock.lock();
        try
        {
            //如果容器满了
            while (CAPACITY == queue.size())
            {
                System.out.println(Thread.currentThread().getName() + "添加失败，容器已经满，需要阻塞");
                notFullCondition.await();
            }
            queue.add(i);
            System.out.println(Thread.currentThread().getName() + "添加:" + i +  "---容器大小：" + queue.size());
            notEmptyCondition.signal();
        } finally
        {
            lock.unlock();
        }

    }

    public Integer take() throws InterruptedException
    {
        lock.lock();
        try
        {
            while (queue.isEmpty())
            {
                System.out.println(Thread.currentThread().getName() + "获取失败，容器是空的，需要阻塞");
                notEmptyCondition.await();
            }
            Integer element = queue.remove();
            System.out.println(Thread.currentThread().getName() + "获取到:" + element +  "---容器大小：" + queue.size());
            notFullCondition.signal();
            return element;
        } finally
        {
            lock.unlock();
        }
    }
}
