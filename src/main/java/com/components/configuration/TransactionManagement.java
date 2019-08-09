package com.components.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author Ian.Su
 * @version $Id: TransactionManagement.java, v 0.1 2017/7/11 18:54 Ian.Su Exp $
 */
@SuppressWarnings("ALL")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactory",
        transactionManagerRef="transactionManager",
        basePackages= { "com.components.dao"}) //设置Repository所在位置
public class TransactionManagement {

    @Autowired
    private DataSource dataSource;

    @Primary
    @Bean(name = "entityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
               // .properties(getVendorProperties(dataSource))
                .packages("com.components.entities") //设置实体类所在位置
                .persistenceUnit("persistenceUnit")
                .build();
    }

//    @Autowired
//    private JpaProperties jpaProperties;
//
//    private Map<String, String> getVendorProperties(DataSource dataSource) {
//        return jpaProperties.getHibernateProperties(dataSource);
//    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }



}
