package com.example.JavaTest;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sparkle6979l
 * @version 1.0
 * @data 2023/5/15 20:51
 */


public class JavaTest {

    @Test
    public void stringtest(){


        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();

    }

    @Test
    public void notifytest() throws InterruptedException {
        String lock = "lock";


        new Thread(new myThreadwait(lock),"0").start();
        new Thread(new myThreadwait(lock),"1").start();
        Thread.sleep(5000);
        new Thread(new myThreadnotify(lock),"2").start();
        new Thread(new myThreadnotify(lock),"3").start();

        Thread.sleep(30000);

    }

    class myThreadwait implements Runnable{
        String loc;

        public myThreadwait(String loc){
            this.loc = loc;
        }


        @Override
        public void run() {
            synchronized (loc){
                System.out.println(Thread.currentThread().getName() + "得到了锁");
                try {
                    Thread.sleep(3000);

                    System.out.println(Thread.currentThread().getName() + "执行wait");

                    loc.wait();

                    System.out.println(Thread.currentThread().getName() + "得到通知，结束运行！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }


    class myThreadnotify implements Runnable{
        String loc;

        public myThreadnotify(String loc){
            this.loc = loc;
        }


        @Override
        public void run() {
            synchronized (loc){
                System.out.println(Thread.currentThread().getName() + "得到了锁");
                try {
                    Thread.sleep(3000);

                    System.out.println(Thread.currentThread().getName() + "执行notify");

                    loc.notify();

                    System.out.println(Thread.currentThread().getName() + "notify，结束运行！");

                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            }
        }
    }

    @Test
    public void collectionTest(){
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("3");
        set.add("215");
        set.add("5");
//        for (Integer integer : set) {
//            System.out.println(integer);
//        }
        System.out.println(set);
        Vector<Integer> ls = new Vector<>();
        set = new TreeSet<>();
        set.add("1");
        set.add("3");
        set.add("215");
        set.add("5");

        System.out.println(set);

        List<Integer> ls1 = new ArrayList<>();
        ls.add(5);
        ls.add(1);
        ls.add(3);
        ls.add(2);
        System.out.println(ls);
        TreeSet<Integer> treeSet = new TreeSet<>();
    }


    @Test
    public void test(){
        col mycol = new col(9);
        for(int i=0;i<10;++i){
            System.out.println(mycol.get());
        }
    }

    class col{

        int size;
        int[] rec;
        int al;
        Random rand;

        public col(int size){
            rand = new Random();

            rec = new int[size];
            for (int i=1;i<=size;++i) {
                rec[i - 1] = i * (i + 1) / 2;
                al += rec[i-1];
            }
        }


        public int get(){
            int randomint = rand.nextInt(al) + 1;
            for(int i=0;i<rec.length-1;++i){
                if(randomint >= rec[i] && randomint < rec[i+1]){
                    return i+1;
                }
            }
            return rec.length;
        }
    }
}
