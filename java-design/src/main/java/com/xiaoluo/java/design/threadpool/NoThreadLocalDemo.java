package com.xiaoluo.java.design.threadpool;

public class NoThreadLocalDemo {

  public static void main(String[] args) throws InterruptedException {

    int threads = 3;
    // CountDownLatch countDownLatch = new CountDownLatch(threads);
    InnerClass innerClass = new InnerClass();
    for(int i = 1; i <= threads; i++) {
      new Thread(() -> {
        for(int j = 0; j < 4; j++) {
          innerClass.add(String.valueOf(j));
          innerClass.print();
        }
        innerClass.set("hello world");
        // countDownLatch.countDown();
      }, "thread - " + i).start();
    }
    //countDownLatch.await();

  }

  private static class InnerClass {

    private StringBuilder counter = new StringBuilder();


    public void add(String newStr) {
      counter.append(newStr);
    }

    public void print() {
      System.out.printf("Thread name:%s , Instance hashcode:%s, Value:%s\n",
      Thread.currentThread().getName(),
      counter.hashCode(),
      counter.toString());
    }

    public void set(String words) {
      counter = (new StringBuilder(words));
      System.out.printf("Set, Thread name:%s ,  Instance hashcode:%s, Value:%s\n",
      Thread.currentThread().getName(),
      counter.hashCode(),
      counter.toString());
    }
  }

}