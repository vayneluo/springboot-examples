package com.xiaoluo.java.design.examination.test2;

import java.util.Scanner;

/**
 * @classname: TeacherTest
 * @description: 测试类
 * @author: Vayne.Luo
 * @date 2019/10/18 11:25
 */
public class TeacherTest {

    public static void main(String[] args) {
        TeacherService teacherService = new TeacherService();
        Scanner sc = new Scanner(System.in);
        // 添加老师实现
        // 控制台输入后，转化成teacher对象，这里是伪代码，需要你自己实现
        Teacher teacher = new Teacher();
        teacherService.add(teacher);
        // 查询老师实现
        long id = sc.nextLong();
        teacherService.findById(id);
    }
}
