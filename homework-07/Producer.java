package com.gupaoedu.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 生产者
 * @author gaopengchao
 * 2019年5月28日
 */
public class Producer implements Runnable
{
    private ArrayBlockingQueue<Integer> queue;
    private int take;
    
    public Producer(ArrayBlockingQueue<Integer> blockQueue,int takee)
    {
        this.queue = blockQueue;
        this.take = takee;
    }
    
    @Override
    public void run()
    {
        try
        {
            queue.put(take);
            System.out.println("生产者" + Thread.currentThread().getName() + "生产了：" + take);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        
    }
}
