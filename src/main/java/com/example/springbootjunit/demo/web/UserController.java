package com.example.springbootjunit.demo.web;

import com.example.springbootjunit.demo.domain.User;
import com.example.springbootjunit.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> listUser() {
        List<User> users = userService.listUser();
        return users;
    }

    @GetMapping("/user/{id}")
    public User findUser(@PathVariable("id") Long id) {
        User user = userService.findUser(id);
        return user;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
}
