package it.wcc.postcode.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

 





public abstract class AbstractAppConfig {
	
	private static final Logger log = LoggerFactory.getLogger(AbstractAppConfig.class);
	
	

	
	/** 
	 *  Use XML less configuration to avoid persistence.xml 
	 * 	https://www.baeldung.com/the-persistence-layer-with-spring-and-jpa
	 * 
	 * 
	 */
	
	protected abstract Environment getEnvironment();
	
	@Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        log.info("Initializing entity manager");
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("it.wcc.postcode.entity");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

//	@Value("${profile.database.driverClassName}")
//	private String driverClassName;

    @Bean
    @Primary
    public DataSource dataSource() {
        log.info("Initializing datasource postcode");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        String driverClassName = getEnvironment().getProperty("database.driverClassName");
        log.debug("driverClassName = {} ", driverClassName);

        dataSource.setDriverClassName(driverClassName);

        String url = getEnvironment().getProperty("database.postcode.url");
        dataSource.setUrl(url);

        String userName = getEnvironment().getProperty("database.postcode.username");
        String password = getEnvironment().getProperty("database.postcode.password");

        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        return dataSource;
    }
	
	@Bean
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    
	private Properties additionalProperties() {
    	String showSql = getEnvironment().getProperty("hibernate.show_sql");
		String hibernateDialect=getEnvironment().getProperty("hibernate.dialect");
		
        Properties properties = new Properties();
        
        properties.setProperty("hibernate.hbm2ddl.auto", "validate");
//        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        properties.setProperty("org.hibernate.FlushMode", "commit");
        properties.setProperty("hibernate.show_sql", showSql);
        properties.setProperty("hibernate.dialect", hibernateDialect);
        return properties;
    }


    //////////////////////////////////////////////

    /*************************************
     @Bean public LocalEntityManagerFactoryBean geEntityManagerFactoryBean() {
     LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
     factoryBean.setPersistenceUnitName("FORESTERIA");
     return factoryBean;
     }

     @Bean public JpaTransactionManager geJpaTransactionManager() {
     JpaTransactionManager transactionManager = new JpaTransactionManager();
     transactionManager.setEntityManagerFactory(geEntityManagerFactoryBean().getObject());
     return transactionManager;
     }
     ******************************/

    @Bean
    public Mapper simpleMapper() {
        log.info("Initializing entity dozer simple mapper");
        DozerBeanMapper simpleMapper = new DozerBeanMapper();

        simpleMapper.setCustomFieldMapper(
                (source, destination, sourceFieldValue, classMap, fieldMapping) -> sourceFieldValue == null
        );
        return simpleMapper;
    }
    
//    @Bean
//	@ValidationMessages
//    public ReloadableResourceBundleMessageSource validationMessageSource() {
//        ReloadableResourceBundleMessageSource validationMessageSource = new ReloadableResourceBundleMessageSource();
//        validationMessageSource.setBasename("classpath:ValidationMessages");
//        return validationMessageSource;
//    }   
   
}
