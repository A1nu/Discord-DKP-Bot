package ee.a1nu.discord_dkp_bot.web.rest;

import ee.a1nu.discord_dkp_bot.web.dto.UserPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestService {
    private final DiscordRestService discordRestService;

    @Autowired
    public UserRestService(DiscordRestService discordRestService) {
        this.discordRestService = discordRestService;
    }

    @GetMapping("/api/user/avatar")
    public Object getUserAvatar() {
        UserPOJO userPOJO = discordRestService.getAuthenticatedUserData();
        return ResponseEntity.ok().body(userPOJO.getAvatar());
    }
}
