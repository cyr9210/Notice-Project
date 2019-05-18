package me.bong.noticeproject.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Filter ssoFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/h2-console/**", "/login**", "/favicon.ico", "/login/google", "/js/**", "/css/**", "/vendor/**").permitAll()
                .anyRequest().authenticated()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                .and()
                    .headers().frameOptions().sameOrigin()
                .and()
                    .csrf().disable()
                .addFilterBefore(ssoFilter, BasicAuthenticationFilter.class);
    }
}
