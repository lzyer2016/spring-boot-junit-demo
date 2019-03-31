package com.example.springbootjunit.demo.dao;

import com.example.springbootjunit.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> listUser();

    User findUser(Long id);

    int saveUser(User user);

    int deleteUser(long uId);

    int updateUser(User user);
}
