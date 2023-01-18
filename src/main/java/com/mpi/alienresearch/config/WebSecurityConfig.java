package com.mpi.alienresearch.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                    .disable()
                .authorizeRequests()
                    //Доступ разрешен всем пользователей
                    .antMatchers("/*").permitAll()
                //Все остальные страницы требуют аутентификации
                .anyRequest().permitAll()
                .and()
                    //Настройка для входа в систему
                    .formLogin()
                    .loginPage("/login")
                    .successHandler(successHandler())
                    .failureHandler(failureHandler())
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessHandler(logoutSuccessHandler())
                    .permitAll();
    }

    private AuthenticationSuccessHandler successHandler() {
        return new AuthenticationSuccessHandler() {
          @Override
          public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
            httpServletResponse.getWriter().append(
                "{"+
                "\"role\" : \""+authentication.getAuthorities().toArray()[0].toString()+"\""+
                ",\"id\" : "+((User)(authentication.getPrincipal())).getId()+
                "}"
            );
            httpServletResponse.setStatus(200);
          }
        };
      }
    
      private AuthenticationFailureHandler failureHandler() {
        return new AuthenticationFailureHandler() {
          @Override
          public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
            httpServletResponse.getWriter().append("Authentication failure");
            httpServletResponse.setStatus(401);
          }
        };
      }

      private LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler() {

          @Override
          public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
              Authentication authentication) throws IOException, ServletException {
            response.setStatus(200);
          }
          
        };
      }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}