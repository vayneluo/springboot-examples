package com.xiaoluo.java.design.guide;

import java.util.Arrays;

/**
 * @classname: MyOnlyStack
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2019/11/28 10:01
 */
public class MyOnlyStack {

    /** 数组维护 **/
    private int[] storage;

    /** 初始容量 **/
    private static final int initialCapacity = 8;

    /** 容量 **/
    private int capacity;

    /** 栈区计数 **/
    private int count;

    /** 增长因子 **/
    private int GROW_UP = 2;

    public MyOnlyStack() {
        this.count = 0;
        this.capacity = initialCapacity;
        this.storage = new int[8];
    }

    /**
     * @description: 带参构造器 入参为指定初始容量
     * @param: [initialCapacity] 初始容量
     * @author: Vayne.Luo
     * @date: 2019/11/28 10:14
     */
    public MyOnlyStack(int initialCapacity) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException("capacity too small.");
        this.capacity = initialCapacity;
        this.count = 0;
        this.storage = new int[initialCapacity];
    }

    /**
     * @description: 入栈
     * @param: [value] 入栈元素
     * @author: Vayne.Luo
     * @date: 2019/11/28 10:14
     */
    public void push(int value){
        if(count == capacity){
            //throw new IllegalArgumentException("stack is empty");
            ensureCapacity();
        }
        storage[count++] = value;
    }

    /**
     * @description: 扩容 当前容量 * 扩容因子
     * @author: Vayne.Luo
     * @date: 2019/11/28 10:13
     */ 
    private void ensureCapacity() {
        int newCapacity = capacity * GROW_UP;
        storage = Arrays.copyOf(storage, newCapacity);
        capacity = newCapacity;
    }

    /**
     * @description: 返回栈顶元素并出栈
     * @author: Vayne.Luo
     * @date: 2019/11/28 10:16
     */ 
    public int pop(){
        count--;
        if(count == -1){
            throw new IllegalArgumentException("stack is empty");
        }
        return storage[count];
    }

    /**
     * @description: 返回栈顶元素不出栈
     * @author: Vayne.Luo
     * @date: 2019/11/28 10:16
     */
    public int peek(){
        if(isEmpty()){
            throw new IllegalArgumentException("stack is empty");
        }else {
            return storage[count - 1];
        }
    }

    //TODO：判断栈是否为空
    private boolean isEmpty() {
        return count == 0;
    }

    //TODO：返回栈中元素的个数
    private int size() {
        return count;
    }

}
