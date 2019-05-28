package com.gupaoedu.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author gaopengchao 2019年5月28日
 */
public class ArrayBlockingQueueTest
{
    public static void main(String[] args)
    {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        
        for (int i = 1;i <= 100;i++)
        {
            new Thread(new Producer(queue,i)).start();
        }
        for (int i = 0;i < 100;i++)
        {
            new Thread(new Consumer(queue)).start();
        }
        
    }
}
