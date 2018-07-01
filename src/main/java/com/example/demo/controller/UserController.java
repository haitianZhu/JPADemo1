package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.jpa.UserJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    private UserJPA userJPA;

    /**
     * 查询用户列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserEntity> list() {

        return userJPA.findAll();
    }

    /**
     * 添加、更新用户
     * @param entity
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public UserEntity save(UserEntity entity) {

        return userJPA.save(entity);
    }

    /**
     * 删除用户
     * @param id 用户编号
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public List<UserEntity> delete(Long id) {

        userJPA.deleteById(id);
        return userJPA.findAll();
    }

    @RequestMapping(value = "/age")
    public List<UserEntity> age() {

        return userJPA.nativeQuery(13);
    }

    /**
     * 分页查询测试
     * @param page
     * @return
     */
    @RequestMapping(value = "/cutPage")
    public List<UserEntity> cutPage(int page) {

        UserEntity user = new UserEntity();
        user.setSize(2);
        user.setPage(page);
        user.setSord("desc");

        // 获取排序对象
        Sort.Direction sort_direction = Sort.Direction.ASC.toString().equalsIgnoreCase(user
                .getSord()) ? Sort.Direction.ASC : Sort.Direction.DESC;
        // 设置排序对象参数
        Sort sort = new Sort(sort_direction, user.getSidx());
        // 创建分页对象
        PageRequest pageRequest = new PageRequest(user.getPage() - 1, user.getSize(), sort);
        // 执行分页查询
        return userJPA.findAll(pageRequest).getContent();
    }
}
