package com.restaurant.utils;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {
    @Bean
    public DataSource dataSource() {
        System.out.println("Setting up datasource");
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://java-revature-justin.cacygpo8xhdd.us-east-2.rds.amazonaws.com:5432/");
        
        //System.out.println(ds.getJdbcUrl());
        ds.setUsername("postgres");
        ds.setPassword("8m2uDctDnUuYSDU3");
        ds.setDriverClassName("org.postgresql.Driver");
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean entityManager() {
        System.out.println("Setting up Session Factory");
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(dataSource());
        sf.setPackagesToScan("com.restaurant.models");
        sf.setHibernateProperties(getHibernateProperties());
        return sf;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        System.out.println("Setting up Transaction Manager");
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(entityManager().getObject());
        return txManager;
    }

    private Properties getHibernateProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");
        props.setProperty("hibernate.jdbc.lob.non_contextual_creation", "true");
        return props;
    }
}
