package com.xiaoluo.java.design.examination.test2;

/**
 * @classname: TeacherDao
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2019/10/18 11:19
 */
public class TeacherDao {

    /***
     * @description: 添加老师
     * @author: Vayne.Luo
     * @date: 2019/10/18 11:19
     */
    public void add(Teacher teacher){
        // Service 里传递teacher对象过来
        // I/O流操作，讲teacher保存到txt中
    }

    /***
     * @description: 根据老师输入的ID查找老师信息
     * @author: Vayne.Luo
     * @date: 2019/10/18 11:19
     */
    public Teacher findById(Long id){
        // 读取文件
        // 查找到匹配的ID，返回Teacher对象
        return new Teacher();
    }
}
