package com.example.demo.jpa;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: NovaStar.tech
 * @Description:
 * @Date: 18:29 2018/7/6
 */
public class Inquirer {

    // 查询条件集合
    List<BooleanExpression> expressions;

    public Inquirer() {
       this.expressions = new ArrayList<>();
    }

    /**
     * 添加查询条件到Query内的查询集合内
     * @param expression
     * @return
     */
    public Inquirer putExpression(BooleanExpression expression) {

        // 添加到条件集合
        expressions.add(expression);
        return this;
    }

    /**
     * 构建出查询findAll函数使用的Predicate接口查询对象<br>
     * 调用buildQuery()可直接作为findAll参数查询条件使用
     * @return
     */
    public Predicate buildQuery() {

        //第一个查询条件对象
        BooleanExpression be = null;
        // 便利所有查询条件，以第一个开始
        for (int i = 0; i < expressions.size(); i++) {
            if (i == 0) {
                be = expressions.get(0);
            } else {
                be = be.and(expressions.get(i));
            }
        }

        return be;
    }

    /**
     * 将Iterable集合转换成ArrayList集合
     * @param iterable  源集合
     * @param <T>   类型
     * @return  arraylist结果
     */
    public <T> List<T> iteratorToList(Iterable<T> iterable) {

        List<T> returnList = new ArrayList();
        Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            returnList.add(iterator.next());
        }

        return returnList;
    }

}
