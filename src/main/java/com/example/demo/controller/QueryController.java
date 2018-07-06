package com.example.demo.controller;

import com.example.demo.entity.GoodEntity;
import com.example.demo.entity.QGoodEntity;
import com.example.demo.jpa.GoodJPA;
import com.example.demo.jpa.Inquirer;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: NovaStar.tech
 * @Description:
 * @Date: 17:38 2018/7/6
 */
@RestController
public class QueryController {

    @Autowired
    private GoodJPA goodJPA;

    // 注入EntityManager
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 单独使用QueryDSL查询
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public List<GoodEntity> query() {

        // querydsl查询实体
        QGoodEntity _good = QGoodEntity.goodEntity;

        // 构建JPA查询对象
        JPAQuery<GoodEntity> jpaQuery = new JPAQuery(entityManager);

        // 返回查询接口
        return jpaQuery
                // 查询字段
                .select(_good)
                // 查询表
                .from(_good)
                // 查询条件
                .where(_good.type.id.eq(Long.valueOf("2")))
                // 返回结果
                .fetch();
    }

    /**
     * SpringDataJPA整合QueryDSL查询
     * @return
     */
    @RequestMapping(value = "/join")
    public List<GoodEntity> join() {

        List<GoodEntity> goods = new ArrayList<>();

        // querydsl查询实体
        QGoodEntity _good = QGoodEntity.goodEntity;


//        // 查询条件
//        BooleanExpression expression = _good.type.id.eq(Long.valueOf("2"));
//
//        // 执行查询
//        Iterator<GoodEntity> iterator = goodJPA.findAll(expression).iterator();
//
//        // 转成list
//        while (iterator.hasNext()) {
//            goods.add(iterator.next());
//        }


        // 自定义查询对象
        Inquirer inquirer = new Inquirer();
        // 添加查询条件
        inquirer.putExpression(_good.type.id.eq(Long.valueOf("2")));
        // 返回查询结果
        goods = inquirer.iteratorToList(goodJPA.findAll(inquirer.buildQuery()));

        return goods;
    }
}
