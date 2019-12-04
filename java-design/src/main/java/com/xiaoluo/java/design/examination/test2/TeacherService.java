package com.xiaoluo.java.design.examination.test2;

/**
 * @classname: TeacherService
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2019/10/18 11:23
 */
public class TeacherService {

    /**
     * @description: 提供给Main方法调用的
     * @param: [teacher] 老师对象
     * @author: Vayne.Luo
     * @date: 2019/10/18 11:24
     */ 
    public void add(Teacher teacher){
        TeacherDao dao = new TeacherDao();
        dao.add(teacher);
    }

    /**
     * @description: 提供给Main方法调用的
     * @param: [teacher] 老师ID
     * @author: Vayne.Luo
     * @date: 2019/10/18 11:24
     */
    public Teacher findById(Long id){
        TeacherDao dao = new TeacherDao();
        Teacher teacher = dao.findById(id);
        return teacher;
    }
}
