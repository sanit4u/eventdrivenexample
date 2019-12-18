package de.sanit4u.evd.example.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

//	@Configuration
//	@EnableWebSecurity
//	protected static class webSecurityConfig extends WebSecurityConfigurerAdapter {
//
//		@Autowired
//		private MongoUserDetailsService userDetailsService;
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			// @formatter:off
//			http.authorizeRequests().anyRequest().authenticated().and().csrf().disable();
//			// @formatter:on
//		}
//
//		@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//		}
//
//		@Override
//		@Bean
//		public AuthenticationManager authenticationManagerBean() throws Exception {
//			return super.authenticationManagerBean();
//		}
//	}
//
//	@Configuration
//	@EnableAuthorizationServer
//	protected static class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
//
//		private TokenStore tokenStore = new InMemoryTokenStore();
//
//		@Autowired
//		@Qualifier("authenticationManagerBean")
//		private AuthenticationManager authenticationManager;
//
//		@Autowired
//		private MongoUserDetailsService userDetailsService;
//
//		@Autowired
//		private Environment env;
//
//		@Override
//		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//
//			// TODO persist clients details
//
//			// @formatter:off
//			clients.inMemory()
//					.withClient("browser")
//					.authorizedGrantTypes("refresh_token", "password")
//					.scopes("ui")
//							
//			.and()
//					.withClient("account-service")
//					.secret(env.getProperty("ACCOUNT_SERVICE_PASSWORD"))
//					.authorizedGrantTypes("client_credentials", "refresh_token")
//					.scopes("server")
//			.and()
//					.withClient("statistics-service")
//					.secret(env.getProperty("STATISTICS_SERVICE_PASSWORD"))
//					.authorizedGrantTypes("client_credentials", "refresh_token")
//					.scopes("server")
//			.and()
//					.withClient("notification-service")
//					.secret(env.getProperty("NOTIFICATION_SERVICE_PASSWORD"))
//					.authorizedGrantTypes("client_credentials", "refresh_token")
//					.scopes("server");
//			// @formatter:on
//		}
//
//		@Override
//		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//			endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager)
//					.userDetailsService(userDetailsService);
//		}
//
//		@Override
//		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//			oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//		}
//	}
}
