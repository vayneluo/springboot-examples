package com.xiaoluo.java.design.guide;

import org.junit.Test;

/**
 * @classname: GuideTest
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2019/11/28 09:13
 */
public class GuideTest {


    @Test
    public void minValuesTest(){
        Integer min = min(new Integer[]{1, 2, 3});
        System.out.println(min);
    }

    private static <T extends Number & Comparable<? super T>> T min(T[] values) {
        if (values == null || values.length == 0) return null;
        T min = values[0];
        for (int i = 1; i < values.length; i++) {
            if (min.compareTo(values[i]) > 0) min = values[i];
        }
        return min;
    }

    @Test
    public void stackTest(){
        MyStack myStack = new MyStack(3);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.push(6);
        myStack.push(7);
        myStack.push(8);
        System.out.println(myStack.peek());//8
        System.out.println(myStack.size());//8
        for (int i = 0; i < 8; i++) {
            System.out.println(myStack.pop());
        }
        System.out.println(myStack.isEmpty());//true
        myStack.pop();
    }
}
