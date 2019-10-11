package com.xiaoluo.java.design.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @classname: ArrayListTest
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2019/9/30 13:16
 */
public class ArrayListTest {

    @Test
    public void testArrayList(){
        List<String> list = new ArrayList<>();
        list.add("222");
        list.add("222");
        list.add("222");
        int[] ints = {1,2,3};
        int i = 0;
        ints[i++] = 2;
        for (int i1 = 0; i1 < ints.length; i1++) {
            System.out.println(ints[i1]);
        }
        System.out.println(ints+ "and i = " +i );

        list.remove(2);
    }

    @Test
    public void testLinkedList(){
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.addFirst("111");
        linkedList.addFirst("222");
        linkedList.addFirst("333");
        String first = linkedList.get(0);
        System.out.println(first);
    }

    class User{

        private String name;

        public final String getPerName(){
            return "";
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    class Son extends User{

    }

    @Test
    public void test(){
        User user = new User();
        user.setName("vayne");
        final User user2 = user;

        System.out.println(user2.getName());
        user2.setName("charlse");
        System.out.println(user2.getName());
    }

}
