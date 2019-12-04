package com.xiaoluo.java.design.examination.test1;

import java.io.*;
import java.util.*;

/**
 * @classname: EmployeeDemo
 * @description: 员工信息录入系统
 * @author: Vayne.Luo
 * @date 2019/10/15 09:15
 */
public class EmployeeDemo {

    /** 员工总数 **/
    private static final int EMPLOYEE_NUMBER = 5;

    /** 输出文件路径 **/
    private static final String FILE_PATH = "D://emp.txt";

    public static void main(String[] args) {
        // 创建员工对象集合
        List<Employee> employeeList = new ArrayList<>();
        // 创建监视器，监控用户输入
        Scanner scanner = new Scanner(System.in);
        // 遍历输入员工基本信息
        for (int i = 0; i < EMPLOYEE_NUMBER; i++) {
            // 创建用户Bean对象
            Employee employee = new Employee();
            // 提示用户录入信息
            System.out.println("请输入第" + (i+1) +  "个员工的信息");
            try {
                System.out.print("员工姓名：");
                String name = scanner.next();
                System.out.print("员工年龄：");
                int age = scanner.nextInt();
                System.out.print("员工工资：");
                double salary = scanner.nextDouble();
                employee.setName(name);
                employee.setAge(age);
                employee.setSalary(salary);
                // 添加用户至员工管理系统
                employeeList.add(employee);
            }catch (Exception e){
                // 异常处理。用户可能输入格式错误，eg:年龄要求int类型， 输入字符串类型将会抛出异常
                System.out.println("用户输入格式错误,请重试！！！");
                // i--,循环次数减1，用户重新输入
                i--;
            }
        }
        // 员工信息排序 按照工资由高到低的排序，如果工资一样按照年龄排序，如果工资年龄都一样，则按照姓名排序
        employeeList.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                // 工资相同
                if(o1.getSalary() == o2.getSalary()){
                    // 年龄相同
                    if(o1.getAge() == o2.getAge()){
                        // 按照姓名排序
                        return o1.getName().compareTo(o2.getName());
                    }else if(o1.getAge() < o2.getAge()) {
                        return 1;
                    }else {
                        return -1;
                    }
                }else if(o1.getSalary() < o2.getSalary()){
                    return 1;
                }else {
                    return -1;
                }
            }
        });
        // 控制台输出员工信息
        System.out.println("|员工信息列表：");
        System.out.println("|名称   年龄   工资");
        for(Employee employee : employeeList){
            System.out.println("|"+ employee.getName()+ "   " + employee.getAge() + "   " + employee.getSalary());
        }
        // 存储员工信息到emp.txt文件中
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write("|员工信息列表：");
            bw.newLine();
            bw.write("|名称   年龄   工资");
            bw.newLine();
            for(Employee employee : employeeList){
                bw.write("|"+ employee.getName()+ "   " + employee.getAge() + "   " + employee.getSalary());
                bw.newLine();
            }
            bw.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
