package com.doppel.leetcode;

import java.util.concurrent.locks.ReentrantLock;

public class IntVisibilityDemo {
    private static int sharedValue = 0; // 不使用volatile
    
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        // 读取线程
        Thread reader = new Thread(() -> {
            int localValue;
            do {
                localValue = sharedValue; // 读取共享变量
                // 添加少量计算让循环不会完全被优化掉
                if (localValue % 100 == 0) {
                    System.out.print("."); // 输出进度
                }
            } while (localValue < 1); // 循环直到值变为1
            
            System.out.println("\nReader thread detected value change to: " + localValue);
        });

        // 写入线程
        Thread writer = new Thread(() -> {
            try {
                Thread.sleep(1000); // 确保reader先启动
                lock.lock();
                sharedValue = 999; // 修改共享变量
                System.out.println("\nWriter thread changed value to: " + sharedValue);
                lock.unlock();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        // 写入线程
        Thread writer2 = new Thread(() -> {
            try {
                Thread.sleep(1000); // 确保reader先启动
                lock.lock();
                sharedValue = 998; // 修改共享变量
                System.out.println("\nWriter thread changed value to: " + sharedValue);
                lock.unlock();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });// 写入线程
        Thread writer3 = new Thread(() -> {
            try {
                Thread.sleep(1000); // 确保reader先启动
                lock.lock();
                sharedValue = 997; // 修改共享变量
                System.out.println("\nWriter thread changed value to: " + sharedValue);
                lock.unlock();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        System.out.println("Starting test without volatile...");
        reader.start();
        writer.start();
        writer2.start();
        writer3.start();

        reader.join(3000); // 最多等待3秒
        if (reader.isAlive()) {
            System.err.println("ERROR: Reader thread failed to see the update!");
            System.err.println("Final value seen by reader: " + sharedValue);
        } else {
            System.out.println("Test completed successfully");
        }
    }
}