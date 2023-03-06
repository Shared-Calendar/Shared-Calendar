package study.sharedcalendar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import study.sharedcalendar.interceptor.AutoLoginInterceptor;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
	private final AutoLoginInterceptor autoLoginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(autoLoginInterceptor)
			.order(1)
			.addPathPatterns("/*")
			.excludePathPatterns("/users/sign-up", "/users/login");
	}
}