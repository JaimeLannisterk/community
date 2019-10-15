package com.springboot.community.helloworld.mapper;

import com.springboot.community.helloworld.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into an_index (name,accountId,token,gmtCreate,gmtModitied,login) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModitied},#{login})")
 public void insert(User user);
}
