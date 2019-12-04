package com.xiaoluo.java.design.examination.test1;

/**
 * @classname: Employee
 * @description: 员工类
 * @author: Vayne.Luo
 * @date 2019/10/15 09:12
 */
public class Employee {

    /** 员工姓名 **/
    private String name;

    /** 员工年龄 **/
    private int age;

    /** 员工工资 **/
    private double salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
