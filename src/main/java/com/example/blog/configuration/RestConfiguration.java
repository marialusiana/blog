package com.example.blog.configuration;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * Rest API Authorization Config
 */
@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled =  true)
@Import(ServerSecurityConfig.class)
public class RestConfiguration extends AuthorizationServerConfigurerAdapter { 

    private static final String RESOURCE_ID = "resource-server-rest-api";
    private static final String RESOURCE_MOBILE = "mobile-resource-id";

	@Autowired
	private DataSource dataSource;
	
	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder oauthClientPasswordEncoder;

	@Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Bean
    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {

        oauthServer.addTokenEndpointAuthenticationFilter(new AuthFilter());

        oauthServer
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()")
        .passwordEncoder(oauthClientPasswordEncoder);
    }

    /**
     * Configure to use Database
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    } 

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
        .pathMapping("/oauth/authorize", "/api/oauth/authorize")
        .pathMapping("/oauth/token", "/api/oauth/token")
        .pathMapping("/oauth/check_token", "/api/oauth/check_token")
        .tokenStore(tokenStore())
        .authenticationManager(authenticationManager)
        .userDetailsService(userDetailsService);
    }

    @Bean
    protected ResourceServerConfiguration cmsResources() {
        ResourceServerConfiguration resource = new ResourceServerConfiguration() {
            public void setConfigurers(List<ResourceServerConfigurer> configurers) {
                super.setConfigurers(configurers);
            }
        };
        resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerConfigurerAdapter() {
            @Override
            public void configure(ResourceServerSecurityConfigurer resources) {
                resources.tokenStore(tokenStore()).resourceId(RESOURCE_ID).stateless(false);;
            }

            @Override
            public void configure(HttpSecurity http) throws Exception {
                handleSecurity(http);
            }
        }));
        resource.setOrder(3);
        return resource;
    }

    @Bean
    protected ResourceServerConfiguration mobileResources() {
        ResourceServerConfiguration resource = new ResourceServerConfiguration() {
            public void setConfigurers(List<ResourceServerConfigurer> configurers) {
                super.setConfigurers(configurers);
            }
        };
        resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerConfigurerAdapter() {
            @Override
            public void configure(ResourceServerSecurityConfigurer resources) {
                resources.tokenStore(tokenStore()).resourceId(RESOURCE_MOBILE).stateless(false);;
            }

            @Override
            public void configure(HttpSecurity http) throws Exception {
                handleSecurity(http);
            }
        }));
        resource.setOrder(4);
        return resource;
    }

    private void handleSecurity(HttpSecurity http) throws Exception {
        http
        // anonymous().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            .antMatchers("/oauth/authorize").permitAll()
        .and()
            .authorizeRequests()
            .antMatchers("/api/oauth/authorize").permitAll()
        .and()
            .authorizeRequests()
            .antMatchers("/oauth/check_token").permitAll()
        .and()
            .authorizeRequests()
            .antMatchers("/api/oauth/check_token").permitAll()
        .and()
            .authorizeRequests()
            .antMatchers("/api/login").permitAll()
        .and()
            .authorizeRequests()
            .antMatchers("/api/user/ready").permitAll()
        .and()
            .authorizeRequests()
            .antMatchers("/api/user/create/password").permitAll()
        .and()
            .authorizeRequests()
            .antMatchers("/api/user/otp/validation").permitAll()
        .and()	
            .authorizeRequests()
            .antMatchers("/oauth/token").permitAll()
        .and()	
            .authorizeRequests()
            .antMatchers("/api/oauth/token").permitAll()
        .and()	
            .authorizeRequests()
            .antMatchers("/api/api-docs.yml").permitAll()
        .and()	
            .authorizeRequests()
            .antMatchers("/api/api-docs").permitAll()
        .and()	
            .authorizeRequests()
            .antMatchers("/api/api-docs/*").permitAll()
        .and()	
            .authorizeRequests()
            .antMatchers("/api/docs.html").permitAll()
        .and()
            .authorizeRequests()
            .antMatchers("/api/swagger-ui/*").permitAll()
        .and()
            .authorizeRequests()
            .antMatchers("/api/user/create/password/link").permitAll()
        .and()
            .authorizeRequests()
            .antMatchers("/api/v2/user/create/password").permitAll()
            
        .and()
        .authorizeRequests()
        .antMatchers("/api/mobile/hello-mobile").permitAll()   
        
        .and()
        .authorizeRequests()
        .antMatchers("/api/mobile/login-mobile").permitAll()   
        
        .and()
        .authorizeRequests()
        .antMatchers(
                "/api/mobile/forgot-password/**",
                "/api/mobile/validate-pin/**").permitAll()
                
        .and()
        .authorizeRequests()
        .antMatchers("/api/mobile/register-user-mobile").permitAll() 
        
        
        .and()
        .authorizeRequests()
        .antMatchers("/api/mobile/register-req-verification").permitAll() 
        
        .and()
        .authorizeRequests()
        .antMatchers("/api/mobile/register-verification-valid").permitAll()   
            
        .and()
        .authorizeRequests()
        .anyRequest()
        // .permitAll()
        .access("isAuthenticated()")
        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
	
}