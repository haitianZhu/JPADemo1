package com.example.demo.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @Author: haitian
 * @Date: 2018/7/1 下午3:11
 * @Description:
 */
@NoRepositoryBean
public interface BaseRepository<T, PK extends Serializable> extends JpaRepository<T, PK> {

}
