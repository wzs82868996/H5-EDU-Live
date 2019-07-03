package com.h5_sdu_live.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity //启动过滤器链
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()//允许资源的跨域访问
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }
//                .authorizeRequests()
//                .antMatchers("/css/**", "/").authenticated()//任何用户
//                .anyRequest().permitAll()
//                .mvcMatchers(HttpMethod.GET, "/**").permitAll()
//                .and()
//                .formLogin().loginPage("/login").successForwardUrl("/").failureForwardUrl("/login-error").usernameParameter("username").passwordParameter("password")
//                .and().exceptionHandling().accessDeniedPage("/eception/403")
//                .and().sessionManagement().maximumSessions(2).expiredUrl("/login?expired");

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/img/**", "/plugins/**");
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //inMemoryAuthentication 从内存中获取
        auth.
                inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("user1").password(new BCryptPasswordEncoder().encode("123456")).roles("USER");
    }
}