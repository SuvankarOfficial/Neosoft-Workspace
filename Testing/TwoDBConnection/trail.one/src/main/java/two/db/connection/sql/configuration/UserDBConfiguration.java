package two.db.connection.sql.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "UserentityManagerFactoryBean",
        transactionManagerRef = "UsertransactionManager",
        basePackages = {"two.db.connection.repository.user"}
)
public class UserDBConfiguration {

    @Autowired
    private Environment environment;

    private Logger logger = LoggerFactory.getLogger(UserDBConfiguration.class);


    @Primary
    @Bean
    public DataSource UserdataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("spring.datasource.mysql.url"));
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.mysql.driverClassName"));
        dataSource.setUsername(environment.getProperty("spring.datasource.mysql.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.mysql.password"));
        logger.info("dataSource: " + dataSource.getUrl() + "\r\n");
        return dataSource;
    }


    @Primary
    @Bean("UserentityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean UserentityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(UserdataSource());

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        props.put("hibernate.show.sql", "true");
        props.put("hibernate.hbm2ddl.auto", "update");
        bean.setJpaPropertyMap(props);
        bean.setPackagesToScan("two/db/connection/sql/entity/user/User.java");
        return bean;
    }


    @Primary
    @Bean("UsertransactionManager")
    public PlatformTransactionManager UsertransactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(UserentityManagerFactoryBean().getObject());
        return manager;
    }
}
