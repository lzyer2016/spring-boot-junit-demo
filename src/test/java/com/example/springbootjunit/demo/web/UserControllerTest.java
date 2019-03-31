package com.example.springbootjunit.demo.web;

import com.example.springbootjunit.demo.domain.User;
import com.example.springbootjunit.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Controller 层测试
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")  // 激活dev配置
@SpringBootTest
@EnableAutoConfiguration
public class UserControllerTest {


    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    /**
     * 初始化 MVC 的环境
     */
    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void listUser() throws Exception {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setName("111");
        user1.setAge(18);
        User user2 = new User();
        user2.setName("222");
        user2.setAge(19);
        users.add(user1);
        users.add(user2);
        when(userService.listUser()).thenReturn(users);
        mockMvc.perform(get("/hello/users")).andExpect(status().isOk())
                .andExpect(jsonPath("$[1].name").exists())
                .andDo(print());
    }

    @Test
    public void findUser() throws Exception {
        User user = new User();
        user.setName("123");
        user.setAge(20);
        when(userService.findUser(1L)).thenReturn(user);
        mockMvc.perform(get("/hello/user/1")).andExpect(status().isOk())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("name").value("123"))
                .andDo(print());
    }

    @Test
    public void saveUser() throws Exception{
        User user = new User();
        user.setId(1L);
        user.setName("123");
        user.setAge(20);
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(user);
        when(userService.saveUser(user)).thenReturn(1);
        mockMvc.perform(post("/hello/user").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void updateUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("123");
        user.setAge(20);
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(user);
        when(userService.updateUser(user)).thenReturn(1);
        mockMvc.perform(put("/hello/user").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteUser() throws Exception {
        when(userService.deleteUser(1L)).thenReturn(1);
        mockMvc.perform(delete("/hello/users/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}