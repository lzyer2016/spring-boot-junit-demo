package com.example.springbootjunit.demo.service;

import com.example.springbootjunit.demo.dao.UserDao;
import com.example.springbootjunit.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> listUser() {
        return userDao.listUser();
    }

    public User findUser(Long id) {
        return userDao.findUser(id);
    }

    public int saveUser(User user) {
        return userDao.saveUser(user);
    }

    public int deleteUser(Long uId) {
        return userDao.deleteUser(uId);
    }

    public int updateUser(User user) {
        return userDao.updateUser(user);
    }
}
