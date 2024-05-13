package com.usermanagement.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.usermanagement.service.jwtsecurity.AuthEntryPointJwtImpl;
import com.usermanagement.service.jwtsecurity.AuthRequestTokenFilter;
import com.usermanagement.service.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwtImpl unauthorizedHandler;


	@Bean
	public AuthRequestTokenFilter authenticationJwtTokenFilter() {
		return new AuthRequestTokenFilter();
	}

	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		        .exceptionHandling()
		        .authenticationEntryPoint(unauthorizedHandler)
		        .and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("/api/auth/**")
				.permitAll()
				.antMatchers("/api/test/**")
				.permitAll()
				.anyRequest()
				.authenticated();

		
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public FilterRegistrationBean coresFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration corsConfiguration = new CorsConfiguration();

		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedOriginPattern("*");
		corsConfiguration.addAllowedHeader("Authorization");
		corsConfiguration.addAllowedHeader("Content-Type");
		corsConfiguration.addAllowedHeader("Accept");
		corsConfiguration.addAllowedMethod("POST");
		corsConfiguration.addAllowedMethod("GET");
		corsConfiguration.addAllowedMethod("DELETE");
		corsConfiguration.addAllowedMethod("PUT");
		corsConfiguration.addAllowedMethod("OPTIONS");
		corsConfiguration.setMaxAge(3600L);

		source.registerCorsConfiguration("/**", corsConfiguration);

		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		return bean;
	}
}
