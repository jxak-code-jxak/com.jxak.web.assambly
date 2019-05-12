package com.jxak.education;


import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@MapperScan(basePackages = {"com.jxak.education.dao"})
@SpringBootApplication
@EnableTransactionManagement
public class EducationApp extends SpringBootServletInitializer implements WebMvcConfigurer {
    /**
     * 项目运行启动
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(EducationApp.class,args);
    }

    /**
     * 构建war包
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        System.out.println("构建war包........");
        return super.configure(builder);
    }

    /**
     * 开启mybatis plus分页功能
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}
