package com.heng.test;

import com.sun.xml.internal.messaging.saaj.util.RejectDoctypeSaxFilter;

import java.util.concurrent.*;

public class ThreadAndRunnable {
    public static void main(String[] args)
    {
//        overSale();
        Sale();
    }

    private static void Sale()
    {
        MyThread2 mRunable = new MyThread2();
        new Thread(mRunable).start();
        new Thread(mRunable).start();
    }

    private static void overSale()
    {
        new MyThread().start();
        new MyThread().start();
    }
}

//会卖出多一倍的票
class MyThread extends Thread {
    private int ticket = 5;
    ThreadLocal<Integer> local = new ThreadLocal<>();


    @Override
    public void run()
    {
        while (true)
        {
            System.out.println("Thread ticket = " + ticket--);
            if (ticket < 0)
            {
                break;
            }
        }
    }
}

// 正常卖出
class MyThread2 implements Runnable {
    private int ticket = 5;

    @Override
    public void run()
    {
        while (true)
        {
            System.out.println("Thread ticket = " + ticket--);
            if (ticket < 0)
            {
                break;
            }
        }
    }
}
