package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.jpa.UserJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: haitian
 * @Date: 2018/7/8 下午7:46
 * @Description:
 */
@Service
@CacheConfig(cacheNames = "userRedis")
public class UserService {

    @Autowired
    private UserJPA userJPA;

    /**
     * 查询用户列表
     * @return
     */
    public List<UserEntity> findAll() {

        return userJPA.findAll();
    }

    /**
     * 添加、更新用户
     * @param entity
     * @return
     */
//    @CachePut注解，不管有没有缓存都会执行方法并将结果缓存起来
//    @CacheEvict注解，移除指定缓存
//    @Cacheable(value = "dd", key = "'hello_' + #id")
    @CachePut(key = "'hello_' + #entity.id")
    public UserEntity save(UserEntity entity) {

        return userJPA.save(entity);
    }

    /**
     * 删除用户
     * @param id 用户编号
     * @return
     */
    @CacheEvict(key = "'hello_' + #id")
    public List<UserEntity> delete(Long id) {

        try {
            userJPA.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userJPA.findAll();
    }

    public List<UserEntity> age() {

        return userJPA.nativeQuery(13);
    }

    /**
     * 分页查询测试
     * @param page
     * @return
     */
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
