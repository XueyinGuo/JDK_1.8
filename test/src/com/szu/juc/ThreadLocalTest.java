package com.szu.juc;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/6 17:48
 */

public class ThreadLocalTest {

    public static void main(String[] args) {

        ThreadLocal<InternalThreadLocalMap> tl = new ThreadLocal<>();
        tl.set(new InternalThreadLocalMap());

    }

}
class InternalThreadLocalMap{

}
