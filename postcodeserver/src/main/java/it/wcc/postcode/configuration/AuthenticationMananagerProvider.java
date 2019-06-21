package it.wcc.postcode.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthenticationMananagerProvider extends WebSecurityConfigurerAdapter {

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		//@formatter:off
//		auth.inMemoryAuthentication()
//			.withUser("habuma").password("password").authorities("ROLE_USER", "ROLE_ADMIN")
//			.and()
//			.withUser("izzy").password("password").authorities("ROLE_USER");
//		//@formatter:on
//	}
	
	// Using NoOpPasswordEncoder for simplicity's sake
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//		http
//        .authorizeRequests()
//            .mvcMatchers("/").permitAll()
//            .anyRequest().authenticated();
//    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
          .anyRequest()
          .authenticated()
//          .and()
//          .httpBasic()
          ;
    }
}
