package com.haizol.batch.mapper;

import com.haizol.batch.bean.LastCommentRsp;
import com.haizol.batch.common.MyMapper;
import com.haizol.batch.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @classname: UserMapper
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2020/9/7 15:58
 */
@Mapper
public interface UserMapper extends MyMapper<User> {

    List<LastCommentRsp> getLimitLastComment(@Param("maxCompId") Long maxCompId);

    void updateUserLastComment(@Param("compId") Long compId, @Param("lastComment") Date lastComment);
}
