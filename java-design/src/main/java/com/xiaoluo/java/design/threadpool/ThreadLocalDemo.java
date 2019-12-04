package com.xiaoluo.java.design.threadpool;

import java.util.concurrent.CountDownLatch;

public class ThreadLocalDemo {

  public static void main(String[] args) throws InterruptedException {

    int threads = 3;
    CountDownLatch countDownLatch = new CountDownLatch(threads);
    InnerClass innerClass = new InnerClass();
    System.out.println("1.0.1分支修改了此处代码");
    System.out.println("1.0.1分支修改了此处代码");
    for(int i = 1; i <= threads; i++) {
      new Thread(() -> {
        for(int j = 0; j < 4; j++) {
          innerClass.add(String.valueOf(j));
          innerClass.print();
        }
        innerClass.set("hello world");
        countDownLatch.countDown();
        System.out.println("1.0.1分支修改了此处代码");
      }, "thread - " + i).start();
    }
    countDownLatch.await();
    System.out.println("1.0.x再次修改了分支");
    System.out.println("1.0.x再次修改了分支");
    System.out.println("1.0.x再次修改了分支");
    System.out.println("1.0.x再次修改了分支");
  }

  private static class InnerClass {

    public void add(String newStr) {
      StringBuilder str = Counter.counter.get();
      Counter.counter.set(str.append(newStr));
    }

    public void print() {
      System.out.printf("Thread name:%s , ThreadLocal hashcode:%s, Instance hashcode:%s, Value:%s\n",
      Thread.currentThread().getName(),
      Counter.counter.hashCode(),
      Counter.counter.get().hashCode(),
      Counter.counter.get().toString());
    }

    public void set(String words) {
      Counter.counter.set(new StringBuilder(words));
      System.out.printf("Set, Thread name:%s , ThreadLocal hashcode:%s,  Instance hashcode:%s, Value:%s\n",
      Thread.currentThread().getName(),
      Counter.counter.hashCode(),
      Counter.counter.get().hashCode(),
      Counter.counter.get().toString());

    }
  }

  private static class Counter {

    private static ThreadLocal<StringBuilder> counter = ThreadLocal.withInitial(StringBuilder::new);

  }

}