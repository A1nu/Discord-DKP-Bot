package ee.a1nu.discord_dkp_bot.web.rest;

import ee.a1nu.discord_dkp_bot.web.dto.GuildDTO;
import ee.a1nu.discord_dkp_bot.web.service.SessionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ee.a1nu.discord_dkp_bot.web.security.OAuth2UserAgentUtils.DISCORD_BOT_USER_AGENT;

@Service
public class DiscordRestService {
    final
    SessionService sessionService;

    @Value("${discord.apiUrl}")
    private String discordApiUrl;

    public DiscordRestService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public List<GuildDTO> getUserGuilds() {
        RestTemplate restTemplate = new RestTemplate();

        String guildsUrl = discordApiUrl + "users/@me/guilds";

        HttpEntity<String> entity = new HttpEntity<>("body", buildHeaders());

        return Arrays.asList(Objects.requireNonNull(restTemplate.exchange(
                guildsUrl,
                HttpMethod.GET,
                entity,
                GuildDTO[].class).getBody()));
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.set(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", sessionService.getSessionBearerToken()));
        headers.set(HttpHeaders.USER_AGENT, DISCORD_BOT_USER_AGENT);
        return headers;
    }
}
