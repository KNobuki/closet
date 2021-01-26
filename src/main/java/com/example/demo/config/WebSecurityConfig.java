package com.example.demo.config;

import com.example.demo.handler.AuthEntryPoint;
import com.example.demo.handler.AuthFailHandler;
import com.example.demo.handler.AuthSuccessHandler;
import com.example.demo.service.user.ClosetUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  @Autowired
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
        .mvcMatchers("/signup")
          .permitAll()
        .anyRequest()
          .authenticated()
      .and()
      .exceptionHandling()
        .authenticationEntryPoint(new AuthEntryPoint())
      .and()
      .formLogin()
        .loginProcessingUrl("/login").permitAll()
          .usernameParameter("email")
          .passwordParameter("pass")
        .successHandler(new AuthSuccessHandler())
        .failureHandler(new AuthFailHandler())
      .and()
      .logout()
        .logoutUrl("/logout")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
      .and()
      .csrf()
        .disable();
  }

  @Autowired
  public void configure(AuthenticationManagerBuilder auth, @Qualifier("closetUserDetailService") ClosetUserDetailsService closetUserDetailsService, PasswordEncoder passwordEncoder) throws Exception {
    auth.eraseCredentials(true)
        .userDetailsService(closetUserDetailsService)
        .passwordEncoder(passwordEncoder);
  }
}
