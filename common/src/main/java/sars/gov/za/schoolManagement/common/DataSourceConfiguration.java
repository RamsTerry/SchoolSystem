/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.common;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author S2028398
 */
@Configuration
@EnableJpaRepositories(basePackages = {"sars.gov.za.schoolManagement.persistence"}, transactionManagerRef = "transactionManager")
@ComponentScan(basePackages = "sars.gov.za.schoolManagement")
@EnableTransactionManagement
public class DataSourceConfiguration {

    @Bean(name = "localContainerEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        Map<String, Object> hibernateProps = new LinkedHashMap<>();
        hibernateProps.put(org.hibernate.cfg.Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        hibernateProps.put(org.hibernate.cfg.Environment.SHOW_SQL, true);
        hibernateProps.put(org.hibernate.cfg.Environment.FORMAT_SQL, true);
        hibernateProps.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "update");
        hibernateProps.put(org.hibernate.cfg.Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);
        hibernateProps.put(org.hibernate.cfg.Environment.GENERATE_STATISTICS, true);
        hibernateProps.put(org.hibernate.cfg.Environment.DEFAULT_BATCH_FETCH_SIZE, 100);
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setPersistenceUnitName("server-persistence-unit");
        localContainerEntityManagerFactoryBean.setPackagesToScan("sars.gov.za.schoolManagement");
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(hibernateProps);
        localContainerEntityManagerFactoryBean.setDataSource(DataSourceUtility.getDatasourceLookup());
        return localContainerEntityManagerFactoryBean;        
    }
    
    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory enableTransactionManagement(@Qualifier("localContainerEntityManagerFactoryBean")LocalContainerEntityManagerFactoryBean entityManagerFactoryBean){
        return entityManagerFactoryBean.getObject();
    }
    
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory managerFactory){
        return new JpaTransactionManager(managerFactory);
    }

}
