package com.example.demo.jpa;

import com.example.demo.entity.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: NovaStar.tech
 * @Description:
 * @Date: 19:21 2018/7/3
 */
public interface LoggerJPA extends JpaRepository<LoggerEntity, Long> {
}
