package ee.a1nu.discord_dkp_bot.web.rest;

import ee.a1nu.discord_dkp_bot.web.dto.UserPOJO;
import ee.a1nu.discord_dkp_bot.web.service.SessionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

import static ee.a1nu.discord_dkp_bot.web.security.OAuth2UserAgentUtils.DISCORD_BOT_USER_AGENT;

@Service("discordRestService")
public class DiscordRestService {
    final
    SessionService sessionService;

    @Value("${discord.apiUrl}")
    private String discordApiUrl;

    public DiscordRestService(SessionService sessionService) {
        this.sessionService = sessionService;
    }


    public UserPOJO getAuthenticatedUserData() {
        RestTemplate restTemplate = new RestTemplate();

        String  userDataUrl = discordApiUrl + "/users/@me";

        HttpEntity<String> entity = new HttpEntity<>("body", buildHeaders());
        try {
            return restTemplate.exchange(
                    userDataUrl,
                    HttpMethod.GET,
                    entity,
                    UserPOJO.class).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.set(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", sessionService.getSessionBearerToken()));
        headers.set(HttpHeaders.USER_AGENT, DISCORD_BOT_USER_AGENT);
        return headers;
    }
}
