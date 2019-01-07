package com.yoler.raisinsconsole.dao.mapper;

import com.yoler.raisinsconsole.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * mybatis-用户Mapper
 */

@Mapper
public interface UserMapper extends BaseMapper<User, Integer> {


}
