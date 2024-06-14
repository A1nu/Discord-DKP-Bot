package ee.a1nu.discord_dkp_bot.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate configureRestTemplate() {
        return new RestTemplate();
    }
}
