package ee.a1nu.discord_dkp_bot.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.RequestEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.JdbcOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequestEntityConverter;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import java.util.Objects;

import static ee.a1nu.discord_dkp_bot.web.security.OAuth2UserAgentUtils.withUserAgent;

@Configuration
@EnableWebSecurity
@EnableJdbcHttpSession
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain configureSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/", "/login","/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/error", "/documentation").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oAuth2LoginConfigurer -> oAuth2LoginConfigurer
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .defaultSuccessUrl("/dashboard", true)
                        .tokenEndpoint(tokenEndpointConfig -> tokenEndpointConfig.accessTokenResponseClient(accessTokenResponseClient()))
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(userService())))
                .logout(LogoutConfigurer::permitAll)
                .build();
    }

    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
        DefaultAuthorizationCodeTokenResponseClient client = new DefaultAuthorizationCodeTokenResponseClient();

        client.setRequestEntityConverter(new OAuth2AuthorizationCodeGrantRequestEntityConverter() {
            @Override
            public RequestEntity<?> convert(OAuth2AuthorizationCodeGrantRequest oauth2Request) {
                return withUserAgent(Objects.requireNonNull(super.convert(oauth2Request)));
            }
        });

        return client;
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> userService() {
        DefaultOAuth2UserService service = new DefaultOAuth2UserService();

        service.setRequestEntityConverter(new OAuth2UserRequestEntityConverter() {
            @Override
            public RequestEntity<?> convert(OAuth2UserRequest userRequest) {
                return withUserAgent(Objects.requireNonNull(super.convert(userRequest)));
            }
        });

        return service;
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService(
            JdbcOperations jdbcOperations,
            ClientRegistrationRepository clientRegistrationRepository) {
        return new JdbcOAuth2AuthorizedClientService(jdbcOperations, clientRegistrationRepository);
    }
}
