package cz.tul.Config;

import cz.tul.controller.ReadOnlyInterceptor;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Conditional(Conditions.ReadOnlyModeEnabled.class)
@Configuration
public class WebConfig implements WebMvcConfigurer {

    public WebConfig() {
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ReadOnlyInterceptor());
    }
}