package com.ce.majiang.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author c__e
 * @date 2020/12/24 21:11
 */
@Configuration
@MapperScan("com.ce.majiang.mapper")
public class MybatisConfig {
}
