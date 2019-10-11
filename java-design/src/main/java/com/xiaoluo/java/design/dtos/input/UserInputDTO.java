package com.xiaoluo.java.design.dtos.input;

import com.google.common.base.Converter;
import com.google.common.collect.Lists;
import com.xiaoluo.java.design.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @classname: UserInputDTO
 * @description: 用户入参
 * @author: Vayne.Luo
 * @date 2019/9/29 14:18
 */
@Data
public class UserInputDTO {

    private String userName;

    private String mobile;

    public User convertToUserReq(){
        UserDTOConvert convert = new UserDTOConvert();
        return convert.doForward(this);
    }


    private static class UserDTOConvert extends Converter<UserInputDTO,User>{

        @Override
        protected User doForward(UserInputDTO userInputDTO) {
            List<String> list = Lists.newArrayList();
            User user = new User();
            BeanUtils.copyProperties(userInputDTO,user);
            return user;
        }

        @Override
        protected UserInputDTO doBackward(User user) {
            throw new AssertionError("不支持逆向转化方法!");
        }
    }

}
