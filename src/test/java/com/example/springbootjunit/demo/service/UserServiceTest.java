package com.example.springbootjunit.demo.service;

import com.example.springbootjunit.demo.dao.UserDao;
import com.example.springbootjunit.demo.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Service 层测试
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")  // 激活dev配置
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE) // 不加载web配置
@EnableAutoConfiguration
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserDao userDao;

    @Test
    public void listUser() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setName("111");
        user1.setAge(18);
        User user2 = new User();
        user2.setName("222");
        user2.setAge(19);
        users.add(user1);
        users.add(user2);
        // mock 数据
        when(userDao.listUser()).thenReturn(users);
        List<User> userList = userService.listUser();
        Assert.assertEquals(2, userList.size());
    }

    @Test
    public void findUser() {
        User user = new User();
        user.setName("123");
        user.setAge(20);
        when(userDao.findUser(1L)).thenReturn(user);
        User user2 = userService.findUser(1L);
        Assert.assertEquals("123", user2.getName());
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setName("123");
        user.setAge(20);
        when(userDao.saveUser(user)).thenReturn(1);
        int id = userService.saveUser(user);
        Assert.assertEquals(1, id);
    }

    @Test
    public void deleteUser() {
        when(userDao.deleteUser(1)).thenReturn(1);
        int id = userService.deleteUser(1L);
        Assert.assertEquals(1, id);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setName("123");
        user.setAge(20);
        user.setId(1L);
        when(userDao.updateUser(user)).thenReturn(1);
        int id = userService.updateUser(user);
        Assert.assertEquals(1, id);
    }
}