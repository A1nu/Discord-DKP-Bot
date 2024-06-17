package ee.a1nu.discord_dkp_bot.web.service;

import ee.a1nu.discord_dkp_bot.web.dto.UserDetailsDTO;
import ee.a1nu.discord_dkp_bot.web.mapper.UserDetailsMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SessionService {
    private final OAuth2AuthorizedClientService authorizedClientService;

    public SessionService(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    public boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.isAuthenticated() && authentication.getPrincipal() != "anonymousUser";
    }

    public String getSessionBearerToken() {
        OAuth2AuthorizedClient authorizedClient =
                this.authorizedClientService.loadAuthorizedClient(
                        "discord",
                        SecurityContextHolder.getContext().getAuthentication().getName());
        return authorizedClient.getAccessToken().getTokenValue();
    }

    public UserDetailsDTO getUserDetails() {
        DefaultOAuth2User principal = (DefaultOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, Object> attributes = principal.getAttributes();
        return UserDetailsMapper.mapAttributesFromSession(attributes);
    }
}
