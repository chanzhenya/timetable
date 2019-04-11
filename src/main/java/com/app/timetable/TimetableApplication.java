package com.app.timetable;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class TimetableApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimetableApplication.class, args);
    }

}
