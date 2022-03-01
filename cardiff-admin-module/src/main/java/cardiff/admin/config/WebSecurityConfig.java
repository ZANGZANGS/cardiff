package cardiff.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/api/v1/join", "/api/v1/login").permitAll()
                    .anyRequest().authenticated()
//                    .anyRequest().permitAll() //개발모드: 모두 허용
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()

                .logout()
                    .logoutSuccessUrl("/logout") //logout 성공시 home으로
                    .logoutSuccessUrl("/login")
                    .permitAll()
                    ;




    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/lib/**", "/bootstrap/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
