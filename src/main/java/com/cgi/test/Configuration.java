package com.cgi.test;

import com.cgi.test.dao.UserDao;
import com.cgi.test.dao.UserDaoImpl;
import com.cgi.test.service.UserService;
import com.cgi.test.view.ViewFirst;
import com.cgi.test.view.ViewSecond;
import com.vaadin.spring.annotation.EnableVaadin;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@org.springframework.context.annotation.Configuration
@EnableVaadin
@ComponentScan({ "com.cgi.test", "com.cgi.test.service", "com.cgi.test.view", "com.cgi.test.dao" })
@PropertySource({ "classpath:hibernate.properties" })
@EnableTransactionManagement
public class Configuration {

    @Autowired
    private Environment environment;

    private UserService userService;

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan(new String[] { "com.cgi.test.model" });
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("connection_pool_size", environment.getRequiredProperty("connection_pool_size"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        localSessionFactoryBean.setHibernateProperties(properties);
        return localSessionFactoryBean;
    }

    @Bean(name = "userDao")
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    @Bean(name = "userService")
    public UserService getUserService() {
        userService = new UserService();
        return userService;
    }

    @Bean(name = "viewFirst")
    public ViewFirst getViewFirst() {
        return new ViewFirst(userService);
    }

    @Bean(name = "viewSecond")
    public ViewSecond getViewSecond() {
        return new ViewSecond(userService);
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory((SessionFactory) sessionFactory().getObject());
        return hibernateTransactionManager;
    }
}
