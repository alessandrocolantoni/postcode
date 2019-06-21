package it.wcc.postcode.authorizationserver.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


/**
 * https://github.com/habuma/spring-security-oauth2-jwt-example/tree/master/oauth-auth-server
 * @author alessandro.colantoni
 *
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	
	 private static final String GRANT_TYPE_PASSWORD = "password";
	 private static final String AUTHORIZATION_CODE = "authorization_code";
	 private static final String REFRESH_TOKEN = "refresh_token";
	    
	    
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    
//    @Override
//	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		   security.checkTokenAccess("isAuthenticated()");
//	}
    
//    @Autowired
//    private TokenStore tokenStore;
   

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
    
//    @Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		//@formatter:off
//		endpoints
//			.tokenStore(tokenStore())
//			.authenticationManager(authenticationManager);
//		//@formatter:on
//	}

//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        endpoints.tokenStore(tokenStore)
//                .authenticationManager(authenticationManager);
//    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("service-account-1")
                .secret("service-account-1-secret")
                .authorizedGrantTypes("client_credentials")
                //.authorizedGrantTypes("client_credentials",GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN)
                //.scopes("resource-server-read", "resource-server-write")
                .scopes("any")
                ;
    }
    
    /**
     * https://stackoverflow.com/questions/48761624/spring-boot-invalid-access-token-error?rq=1
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {     
        security.allowFormAuthenticationForClients().checkTokenAccess("permitAll()");       
    }
    
//    @Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		//@formatter:off
//		clients
//			.inMemory()
//				.withClient("myclient")
//				.secret("secret")
//				.authorizedGrantTypes("authorization_code", "implicit", "password", "client_credentials", "refresh_token")
//				.scopes("read")
//				.redirectUris("http://localhost:9191/x")
//				.accessTokenValiditySeconds(86400); // 24 hours
//		//@formatter:on
//	}
    
 // A token store bean. In-memory token store
// 	@Bean
// 	public TokenStore tokenStore() {
// 		return new InMemoryTokenStore();
// 	}
}