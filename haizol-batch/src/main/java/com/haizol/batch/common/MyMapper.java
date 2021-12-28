package com.haizol.batch.common;

import tk.mybatis.mapper.additional.update.force.UpdateByPrimaryKeySelectiveForceMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>, UpdateByPrimaryKeySelectiveForceMapper<T> {

}
