package com.szu.juc.thread_pool;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/2/26 23:13
 */

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest {

    static int nums[];

    static {
        nums = new int[10000000];
        Random random = new Random();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(10);
            sum += nums[i];
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        Task task = new Task(0, nums.length - 1, 50000, nums);
        forkJoinPool.execute(task);
        Integer join = task.join();
        System.out.println(join);
    }

}

class Task extends RecursiveTask<Integer>{

    int start;
    int end;
    int max;
    int nums[];

    public Task(int start, int end, int max, int[] nums) {
        this.start = start;
        this.end = end;
        this.max = max;
        this.nums = nums;
    }

    @Override
    protected Integer compute() {
        int d = end - start;
        if (d <= max){
            Integer sum = 0;
            for (int i = start; i < end; i++)
                sum += nums[i];
            return sum;
        }
        int mid = start + d / 2;
        Task task = new Task(start, mid, max, nums);
        Task task2 = new Task( mid, end, max, nums);
        task.fork();
        task2.fork();
        return task.join() + task2.join();
    }
}
