package com.springboot.community.helloworld.mapper;

import com.springboot.community.helloworld.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into an_index (name,accountId,token,gmt_create,gmt_modified,login) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{login})")
 public void insert(User user);
    @Select("select * from an_index where token =#{token}")
    public User findByToken(@Param("token") String token);
}
