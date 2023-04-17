package com.example.demo_spring_2.config;

//import com.example.demo_spring_2.handler.CustomAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Profile("dev")
public class WebSecurityConfig{


    @Autowired
    @Qualifier("currentUserDetailServiceImpl")
    private UserDetailsService userDetailsService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.csrf().disable()
                        .authorizeHttpRequests()
                        .requestMatchers("/user/register").permitAll()
                        .requestMatchers("/").permitAll()
                        .anyRequest()
                        .authenticated()
                        .and()
                        .formLogin()
                        .loginPage("/")
                        .usernameParameter("loginUserName")
                        .passwordParameter("loginPassword")
                        .defaultSuccessUrl("/user/home");

//                .authorizeRequests()
////                .antMatchers("/actuator/**").permitAll()
////                .antMatchers("/user/register").permitAll()
////                .antMatchers("/user/activate").permitAll()
////                .antMatchers("/user/image").permitAll()
////
////                .antMatchers("/images/**").permitAll()
////                .antMatchers("/admin/**").hasAuthority("ADMIN")
////                .antMatchers("/verifyError").permitAll()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/home")
//                .failureUrl("/login?error=Bad credentials")
////                .failureHandler(new CustomAuthenticationFailureHandler())
//                .permitAll()
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID")
//                .and()
//                .rememberMe()
////                .tokenValiditySeconds(2*604800) // 1 week
//                .tokenValiditySeconds(3000) // 1 week
//                .and();
        return http.build();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//                .authorizeRequests()
//                .antMatchers("/actuator/**").permitAll()
//                .antMatchers("/user/register").permitAll()
//                .antMatchers("/user/activate").permitAll()
//                .antMatchers("/user/image").permitAll()
//
//                .antMatchers("/images/**").permitAll()
////                .antMatchers("/admin/**").hasAuthority("ADMIN")
//                .antMatchers("/verifyError").permitAll()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/home")
//                .failureUrl("/login?error=Bad credentials")
////                .failureHandler(new CustomAuthenticationFailureHandler())
//                .permitAll()
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID")
//                .and()
//                .rememberMe()
////                .tokenValiditySeconds(2*604800) // 1 week
//                .tokenValiditySeconds(3000) // 1 week
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder);
//    }
//    @Override





    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }

}