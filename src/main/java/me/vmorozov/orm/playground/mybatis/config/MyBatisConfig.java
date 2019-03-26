package me.vmorozov.orm.playground.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"me.vmorozov.orm.playground.mybatis.dao"})
public class MyBatisConfig {
}
