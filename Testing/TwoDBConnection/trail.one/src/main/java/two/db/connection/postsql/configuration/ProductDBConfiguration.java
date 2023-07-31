package two.db.connection.postsql.configuration;

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
        entityManagerFactoryRef = "productEntityManagerFactoryBean",
        transactionManagerRef = "productTransactionManager"
)
public class ProductDBConfiguration {

    @Autowired
    private Environment environment;

    private Logger logger = LoggerFactory.getLogger(ProductDBConfiguration.class);


    @Primary
    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.postgres")
    public DataSource productDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("spring.su-datasource.postgres.url"));
        dataSource.setDriverClassName(environment.getProperty("spring.su-datasource.postgres.driverClassName"));
        dataSource.setUsername(environment.getProperty("spring.su-datasource.postgres.username"));
        dataSource.setPassword(environment.getProperty("spring.su-datasource.postgres.password"));
        logger.info("dataSource: " + dataSource.getUrl() + "\r\n");
        return dataSource;
    }


    @Primary
    @Bean("productEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean productEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(productDataSource());

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.show.sql", "true");
        props.put("hibernate.hbm2ddl.auto", "update");
        bean.setJpaPropertyMap(props);
        bean.setPackagesToScan("two.db.connection.postsql.repository.product");
        return bean;
    }


    @Primary
    @Bean("productTransactionManager")
    public PlatformTransactionManager productTransactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(productEntityManagerFactoryBean().getObject());
        return manager;
    }

}
