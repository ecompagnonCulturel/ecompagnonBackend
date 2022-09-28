package uqtr.ecompagnon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import uqtr.ecompagnon.jwt.AuthFailureHandler;
import uqtr.ecompagnon.jwt.JwtConfig;
import uqtr.ecompagnon.jwt.JwtTokenVerify;
import uqtr.ecompagnon.jwt.JwtUsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//Permet d'utiliser @preauthorize dans le controlleur
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final UserDetailsService userDetailsService;
    private static final String[] AUTH_WHITELIST = {
            "/api/registration/add","/api/registration/forgetPwd",
            "/api/registration/confirm",
            "/api/registration/confirmPwd",
            "/api/registration/initPwd",
            "/api/registration/resendMail",
            "/confirmAccount.html",
            "/courrielValide",
            "/lienExpire",
            "accountNotExist",
            "initedPassword",
            "initPassword",
            "urlNotOK",
            "/api/login/**",
            "/api/registration/initPwdNoToken",
            "/api/registration/confirmPwdTokenAndInitPwd",
            "/resources/**",
            "/css/**",
            "/swagger-ui*/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**"
    };

    public ApplicationSecurityConfig(@Qualifier("appAuthenticator") UserDetailsService userDetailsService,
                                     PasswordEncoder passwordEncoder,
                                     JwtConfig jwtConfig,
                                     SecretKey secretKey) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

/*
    public ApplicationSecurityConfig(@Qualifier("adminAuthenticator") UserDetailsService userDetailsService,
                                     PasswordEncoder passwordEncoder,
                                     JwtConfig jwtConfig,
                                     SecretKey secretKey) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }*/


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.exceptionHandling().authenticationEntryPoint(new AuthFailureHandler());
        //production https
        http.requiresChannel()
                .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
                .requiresSecure();

        http
        .cors().and()
        .csrf().disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
        .addFilterAfter(new JwtTokenVerify(secretKey, jwtConfig), JwtUsernamePasswordAuthenticationFilter.class)
         .authorizeRequests()
                    .antMatchers(AUTH_WHITELIST)
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;

    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new
                WebMvcConfigurer() {

                    @Override public void addCorsMappings(CorsRegistry registry) {
                        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT",
                                "DELETE").allowedOrigins("*") .allowedHeaders("*"); } };
    }

   @Bean
   @Primary
   public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
      // System.out.println("Config is starting.");
       ObjectMapper objectMapper = builder.createXmlMapper(false).build();
       objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
       return objectMapper;
   }

}

