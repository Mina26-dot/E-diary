package com.iktpreobuka.elektronskidnevnik1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig {

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@SuppressWarnings({ "removal" })
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests().anyRequest().authenticated().and().httpBasic()
				.authenticationEntryPoint(authenticationEntryPoint);
		return http.build();
	}

//
	@Bean
	public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
		auth
			.inMemoryAuthentication()
			.withUser("admin").password("{noop}admin14993").roles("ADMIN")
			.and()
    		.withUser("roditelj").password("{noop}password").roles("RODITELJ")
    		.and()
    		.withUser("ucenik").password("{noop}password1").roles("UCENIK")
    		.and()
    		.withUser("nastavnik").password("{noop}password2").roles("NASTAVNIK");
 
		return auth.build();
	}

	// ---------------------------------------------------------------------------

	@Value("${spring.security.user.name}")
	private String username;

	@Value("${spring.security.user.password}")
	private String password;

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws Exception
	// {
//        auth
//            .inMemoryAuthentication()
//            .withUser(username)
//            .password("{noop}" + password)
//            .roles("ADMIN");
//    	auth
//    		.inMemoryAuthentication()
//    		.withUser("admin").password("{noop}admin14993").roles("ADMIN")
//    		.and()
//    		.withUser("roditelj").password("{noop}password").roles("RODITELJ")
//    		.and()
//    		.withUser("ucenik").password("{noop}password1").roles("UCENIK")
//    		.and()
//    		.withUser("nastavnik").password("{noop}password2").roles("NASTAVNIK");
	// }

	@SuppressWarnings({ "removal" })
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/parent/**")
				.hasAnyRole("RODITELJ", "NASTAVNIK", "UCENIK").requestMatchers("/student/**")
				.hasAnyRole("RODITELJ", "NASTAVNIK", "UCENIK").requestMatchers("/teacher/**")
				.hasAnyRole("RODITELJ", "NASTAVNIK", "UCENIK").requestMatchers("/grades/**")
				.hasAnyRole("RODITELJ", "NASTAVNIK", "UCENIK").requestMatchers("/absence/**")
				.hasAnyRole("RODITELJ", "NASTAVNIK", "UCENIK").requestMatchers("/user/**").hasAnyRole("ADMIN")
				.requestMatchers("/subject/**").hasAnyRole("RODITELJ", "NASTAVNIK", "UCENIK")
				.requestMatchers("/student/**").hasAnyRole("RODITELJ", "NASTAVNIK", "UCENIK")
				.requestMatchers("/class/**").hasAnyRole("RODITELJ", "NASTAVNIK", "UCENIK")

				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
				.logoutUrl("/logout").logoutSuccessUrl("/logout?logout").permitAll();

//            .httpBasic();
	}

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

}
