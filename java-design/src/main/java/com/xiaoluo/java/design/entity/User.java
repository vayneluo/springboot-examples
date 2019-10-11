package com.xiaoluo.java.design.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * @classname: User
 * @description: 用户实体
 * @author: Vayne.Luo
 * @date 2019/9/29 14:22
 */
@Data
//@Builder
public class User {

    private String userName;

    private String mobile;

    private String password;

    private LocalDate createTime;

}
