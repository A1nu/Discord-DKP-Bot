package ee.a1nu.discord_dkp_bot.web.rest;

import ee.a1nu.discord_dkp_bot.web.service.SessionService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

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

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.set(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", sessionService.getSessionBearerToken()));
        headers.set(HttpHeaders.USER_AGENT, DISCORD_BOT_USER_AGENT);
        return headers;
    }
}
