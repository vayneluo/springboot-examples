package com.xiaoluo.java.design.entity;

import lombok.Data;

import java.io.*;

/**
 * @classname: Customer
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2019/9/30 15:40
 */
public class Customer implements Serializable {

    private String name;

    private int age;

    private String test;

    public static void main(String[] args) {
        try {
//            Customer customer = new Customer();
//            customer.setAge(20);
//            customer.setName("vayne");
//            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("D:/Customer.txt")));
//            outputStream.writeObject(customer);
//            System.out.println("序列化成功~！");

            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("D:/Customer.txt")));
            Customer read = (Customer) inputStream.readObject();
            System.out.println("反序列化成功~！");
            System.out.println(read);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
