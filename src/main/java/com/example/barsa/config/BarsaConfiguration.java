package com.example.barsa.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BarsaConfiguration {
    @Bean(name = "oracleDataSource")
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();

        dataSourceBuilder.driverClassName("oracle.jdbc.OracleDriver");
        dataSourceBuilder.url("jdbc:oracle:thin:@localhost:1521/XEPDB1");
        dataSourceBuilder.username("BARSA");
        dataSourceBuilder.password("BARSA");

        return dataSourceBuilder.build();
    }
}
