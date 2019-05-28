package com.gupaoedu.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 消费者
 * @author gaopengchao
 * 2019年5月28日
 */
public class Consumer implements Runnable
{
    private ArrayBlockingQueue<Integer> queue;
    
    public Consumer(ArrayBlockingQueue<Integer> blockQueue)
    {
        this.queue = blockQueue;
    }
    
    @Override
    public void run()
    {
        try
        {
            Integer take = queue.take();
            System.out.println("消费者" + Thread.currentThread().getName() + "消费了：" + take);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        
    }
}
