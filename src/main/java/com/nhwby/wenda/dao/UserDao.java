package com.nhwby.wenda.dao;

import com.nhwby.wenda.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {

  String s1 = "user";
     String INSET_FIELDS = " name, password, salt, head_url ";
   String SELECT_FIELDS = " id, name, password, salt, head_url";

    @Insert({"insert into ", "s1", "(", INSET_FIELDS,
            ") values (#{name},#{password},#{salt},#{headUrl})"})
    int addUser(User user);

    @Select({"select ", "SELECT_FIELDS", " from "," s1", " where id=#{id}"})
    User selectById(int id);

    @Select({"select ", "SELECT_FIELDS", " from ","s1", " where name=#{name}"})
     User selectByName(String name);

    @Update({"update ", "s1", " set password=#{password} where id=#{id}"})
    void updatePassword(User user);

    @Delete(value = {"delete from ", "s1", " where id=#{id}"})
    void deleteById(int id);
}
