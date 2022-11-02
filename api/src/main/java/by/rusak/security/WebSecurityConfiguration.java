package by.rusak.security;

import by.rusak.security.filter.AuthenticationTokenFilter;
import by.rusak.security.jwt.JwtTokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userProvider;

    private final JwtTokenHelper tokenUtils;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userProvider)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf() // механизм защиты от csrf-угрозы
                .disable()// механизм защиты от csrf-угрозы
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() // авторизовать запросы след. образом
                /*For swagger access only*/
                .antMatchers("/v2/api-docs/**", "/configuration/ui/**", "/swagger-resources/**", "/configuration/security/**", "/swagger-ui/**", "/webjars/**").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/index").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //.antMatchers("/actuator/**").permitAll()
                //.antMatchers("/guest/**").permitAll()
                //.antMatchers(" /activate/**").permitAll()
                //.antMatchers("/rest/**").permitAll()
                //.antMatchers("/authentication/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/registration/**").permitAll()
                .antMatchers("/cars/**").permitAll()
                .antMatchers("/schedule/**").permitAll()
                .antMatchers("/users/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers("/createord/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "MODERATOR")
                .anyRequest()
                .authenticated(); // каждый запрос д.б. аутентифицирован

        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(authenticationManagerBean()), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean(AuthenticationManager authenticationManager) throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter(tokenUtils, userProvider);
        authenticationTokenFilter.setAuthenticationManager(authenticationManager);
        return authenticationTokenFilter;
    }

    //For swagger access only
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/v2/api-docs", "/configuration/ui/**", "/swagger-resources/**", "/configuration/security/**", "/swagger-ui.html", "/webjars/**");
    }


}
