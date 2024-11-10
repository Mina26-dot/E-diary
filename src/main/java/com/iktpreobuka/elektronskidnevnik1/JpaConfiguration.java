package com.iktpreobuka.elektronskidnevnik1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class JpaConfiguration {

//    private final DataSource dataSource;
//
//    public JpaConfiguration(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("com.iktpreobuka.elektronskidnevnik1.entity");
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        em.setJpaProperties(hibernateProperties);
//
//        return em;
//    }
	
	private final DataSource dataSource;

    public JpaConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan("com.iktpreobuka.elektronskidnevnik1.entity");
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        entityManagerFactory.setJpaProperties(hibernateProperties);

        entityManagerFactory.setEntityManagerFactoryInterface(jakarta.persistence.EntityManagerFactory.class);

        return entityManagerFactory;
    }
}
