package it.wcc.postcode.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//@ComponentScans(value = {@ComponentScan("it.sme.foresteria.aps.dao"),
//        @ComponentScan("it.sme.foresteria.aps.service"),
//        @ComponentScan("it.sme.foresteria.aps.controller"),
//        @ComponentScan("it.sme.foresteria.aps.validator")})
@PropertySource("classpath:application.yml")
public class AppConfig extends AbstractAppConfig{

    @SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);


    @Autowired
    private Environment environment;

    

	@Override
	protected Environment getEnvironment() {
		return this.environment;
	}   
	
}
