package com.gupaoedu.simplequeue;

import java.util.Random;

/**
 * @author gaopengchao
 * 2019年5月22日
 */
public class BlockingQueueTest
{
    public static void main(String[] args)
    {
        BlockingQueue queue = new BlockingQueue();
        Random random = new Random();
        for (int i = 0; i < 10; i++)
        {
            new Thread(() -> {
                try
                {
                    while(true)
                    {
                        queue.put(random.nextInt(100));
                        Thread.sleep(100);
                    }
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 8; i++)
        {
            new Thread(() -> {
                try
                {
                    while(true)
                    {
                        queue.take();
                        Thread.sleep(100);
                    }
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
