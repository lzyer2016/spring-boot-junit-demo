package com.example.springbootjunit.demo.dao;

import com.example.springbootjunit.demo.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Dao 层单元测试
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")  // 激活dev配置
@MapperScan(basePackages = "com.example.springbootjunit.demo.dao") //扫描dao的包
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE) // 不加载web配置
@EnableAutoConfiguration
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void listUser() {
        List<User> users = userDao.listUser();
        Assert.assertEquals(3, users.size());
    }

    @Test
    public void findUser() {
        User user = userDao.findUser(2L);
        Assert.assertEquals(21, user.getAge().intValue());
        Assert.assertEquals("lisi", user.getName());
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setName("zhaoliu");
        user.setAge(30);
        int id = userDao.saveUser(user);
        Assert.assertEquals(1, id);
    }

    @Test
    public void deleteUser() {
        long uId = 3L;
        int id = userDao.deleteUser(uId);
        Assert.assertEquals(1, id);
    }

    @Test
    public void updateUser() {
        User user = userDao.findUser(1L);
        Assert.assertEquals("zhangsan", user.getName());
        user.setName("zhangsan2");
        user.setAge(18);
        int id = userDao.updateUser(user);
        Assert.assertEquals(1, id);
    }
}