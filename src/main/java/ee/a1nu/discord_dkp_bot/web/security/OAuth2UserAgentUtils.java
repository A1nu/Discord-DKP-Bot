package ee.a1nu.discord_dkp_bot.web.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;

public class OAuth2UserAgentUtils {
    static RequestEntity<?> withUserAgent(RequestEntity<?> request) {
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(request.getHeaders());
        headers.add(HttpHeaders.USER_AGENT, DISCORD_BOT_USER_AGENT);

        return new RequestEntity<>(request.getBody(), headers, request.getMethod(), request.getUrl());
    }

    public static final String DISCORD_BOT_USER_AGENT = "DiscordBot";
}