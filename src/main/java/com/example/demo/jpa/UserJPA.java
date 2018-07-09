package com.example.demo.jpa;

import com.example.demo.base.BaseRepository;
import com.example.demo.entity.UserEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: NovaStar.tech
 * @Description:
 * @Date: 18:38 2018/6/29
 */
@Transactional
public interface UserJPA extends BaseRepository<UserEntity, Long> {

    @Query(value = "select * from t_user where age > ?1", nativeQuery = true)
    List<UserEntity> nativeQuery(int age);

    UserEntity findUserEntityByUsernameAndPassword(String username, String password);

    UserEntity findUserEntityByUsername(String username);

}
