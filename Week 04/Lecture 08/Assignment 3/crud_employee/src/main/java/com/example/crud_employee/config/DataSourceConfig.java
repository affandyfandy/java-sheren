package com.example.crud_employee.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource1.url}")
    private String dataSource1Url;

    @Value("${spring.datasource1.username}")
    private String dataSource1Username;

    @Value("${spring.datasource1.password}")
    private String dataSource1Password;

    @Value("${spring.datasource1.driver-class-name}")
    private String dataSource1DriverClassName;

    @Value("${spring.datasource2.url}")
    private String dataSource2Url;

    @Value("${spring.datasource2.username}")
    private String dataSource2Username;

    @Value("${spring.datasource2.password}")
    private String dataSource2Password;

    @Value("${spring.datasource2.driver-class-name}")
    private String dataSource2DriverClassName;

    @Bean(name = "dataSource1")
    public DataSource dataSource1() {
        return DataSourceBuilder.create()
                .url(dataSource1Url)
                .username(dataSource1Username)
                .password(dataSource1Password)
                .driverClassName(dataSource1DriverClassName)
                .build();
    }

    @Bean(name = "jdbcTemplate1")
    public JdbcTemplate jdbcTemplate1(@Qualifier("dataSource1") DataSource dataSource1) {
        return new JdbcTemplate(dataSource1);
    }

    @Bean(name = "dataSource2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create()
                .url(dataSource2Url)
                .username(dataSource2Username)
                .password(dataSource2Password)
                .driverClassName(dataSource2DriverClassName)
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
