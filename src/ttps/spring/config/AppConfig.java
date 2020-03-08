package ttps.spring.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ttps.spring")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig implements WebMvcConfigurer {
	
	public static final String FRONT_URL = "http://localhost:4200";

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins(FRONT_URL)
            .allowedMethods("GET", "PUT", "DELETE", "POST", "OPTIONS")
  		  	.allowedHeaders("X-Auth-Token", "Content-Type", "Authorization")
  		  	.allowCredentials(false)
			.maxAge(3600);
	}
	
}
