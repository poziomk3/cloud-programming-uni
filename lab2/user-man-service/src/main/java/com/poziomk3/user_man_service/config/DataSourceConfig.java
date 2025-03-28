package com.poziomk3.user_man_service.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5433/usermandb");
        config.setUsername("user");
        config.setPassword("password");
        config.setDriverClassName("org.postgresql.Driver");

        return new HikariDataSource(config);
    }
}
