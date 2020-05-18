package lt.eimantas.eshop.reqResLogger;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyCustomInterceptor());
    }
}


//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        RestTemplate restTemplate = builder.build();
//        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
//        restTemplate.setInterceptors(Collections.singletonList(new LoggingInterceptor()));
//        return restTemplate;
//    }


//    @Bean
//    public RestTemplate restTemplate() {
//        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
//		RestTemplate restTemplate = new RestTemplate(factory);
//		List<ClientHttpRequestInterceptor> interceptorList = new ArrayList<>();
//		interceptorList.add(new LoggingInterceptor());
//		restTemplate.setInterceptors(interceptorList);
//        return restTemplate;
//}