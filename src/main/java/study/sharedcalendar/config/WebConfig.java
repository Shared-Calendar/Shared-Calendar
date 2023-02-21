package study.sharedcalendar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import study.sharedcalendar.interceptor.AutoLoginInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AutoLoginInterceptor())
                .order(1)
                .addPathPatterns("/*")
                .excludePathPatterns("/users/sign-up", "users/sign-in");
    }

}
