package com.example.crud_employee.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.boot.jdbc.DataSourceBuilder;

@Configuration
public class DataSourceConfig {

    @Bean(name = "dataSource1")
    public DataSource dataSource1() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/week4_employee")
                .username("root")
                .password("")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean(name = "jdbcTemplate1")
    public JdbcTemplate jdbcTemplate1(@Qualifier("dataSource1") DataSource dataSource1) {
        return new JdbcTemplate(dataSource1);
    }

    @Bean(name = "dataSource2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/week4_employeeclone")
                .username("root")
                .password("")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean(name = "jdbcTemplate2")
    public JdbcTemplate jdbcTemplate2(@Qualifier("dataSource2") DataSource dataSource2) {
        return new JdbcTemplate(dataSource2);
    }

    @Bean(name = "dataSource1TransactionManager")
    public PlatformTransactionManager dataSource1TransactionManager(@Qualifier("dataSource1") DataSource dataSource1) {
        return new DataSourceTransactionManager(dataSource1);
    }

    @Bean(name = "dataSource2TransactionManager")
    public PlatformTransactionManager dataSource2TransactionManager(@Qualifier("dataSource2") DataSource dataSource2) {
        return new DataSourceTransactionManager(dataSource2);
    }
}
