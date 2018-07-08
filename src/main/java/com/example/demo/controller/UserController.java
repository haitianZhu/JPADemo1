package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: NovaStar.tech
 * @Description:
 * @Date: 18:26 2018/6/29
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserEntity> list() {

        return userService.findAll();
    }

    /**
     * 添加、更新用户
     * @param entity
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public UserEntity save(UserEntity entity) {

        return userService.save(entity);
    }

    /**
     * 删除用户
     * @param id 用户编号
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public List<UserEntity> delete(Long id) {
        System.out.println("1112" + id);
        return userService.delete(id);
    }

    @RequestMapping(value = "/age")
    public List<UserEntity> age() {

        return userService.age();
    }

    /**
     * 分页查询测试
     * @param page
     * @return
     */
    @RequestMapping(value = "/cutPage")
    public List<UserEntity> cutPage(int page) {

        return userService.cutPage(page);
    }
}
