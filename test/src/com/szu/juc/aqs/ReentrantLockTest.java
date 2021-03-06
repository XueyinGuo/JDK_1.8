package com.szu.juc.aqs;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/2/27 11:31
 */

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        lock.lock();

    }
}
