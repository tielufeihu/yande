package game605.common.config;

import game605.common.Interceptors.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ImgInterceptor getImgInter(){
        return new ImgInterceptor();
    }

    @Bean
    public TagInterceptor getTagInter(){
        return new TagInterceptor();
    }

    @Bean
    public UserInterceptor getUserInter(){
        return new UserInterceptor();
    }

    @Bean
    public RoleInterceptor getRoleInter(){
        return new RoleInterceptor();
    }

    @Bean
    public AuthInterceptor getAuthInter(){
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(new AllInterceptor());
        // registry.addInterceptor(getUserInter()).addPathPatterns("/user/*");
        registry.addInterceptor(getRoleInter()).addPathPatterns("/role/*");
        registry.addInterceptor(getAuthInter()).addPathPatterns("/auth/*");
        registry.addInterceptor(getImgInter()).addPathPatterns("/img/*");
        registry.addInterceptor(getTagInter()).addPathPatterns("/tag/*");
    }
}
