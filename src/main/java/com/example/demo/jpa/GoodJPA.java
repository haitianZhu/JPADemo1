package com.example.demo.jpa;

import com.example.demo.entity.GoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @Author: NovaStar.tech
 * @Description:
 * @Date: 17:39 2018/7/6
 */
public interface GoodJPA extends JpaRepository<GoodEntity, Long>, QuerydslPredicateExecutor<GoodEntity> {

}
