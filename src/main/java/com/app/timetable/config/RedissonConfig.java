package com.app.timetable.config;

import com.app.timetable.common.aspect.LogAspect;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author Judith
 * @date 2019/7/7 15:12
 * @description
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redisson() throws IOException {
        Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-config.yml"));
        return Redisson.create(config);
    }

    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }
}
