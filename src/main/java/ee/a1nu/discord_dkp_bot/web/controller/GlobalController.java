package ee.a1nu.discord_dkp_bot.web.controller;

import ee.a1nu.discord_dkp_bot.web.dto.UserPOJO;
import ee.a1nu.discord_dkp_bot.web.rest.DiscordRestService;
import ee.a1nu.discord_dkp_bot.web.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {
    @Value("${discord.cdnUrl}")
    private String discordCdnUrl;


    private final DiscordRestService discordRestService;
    private final SessionService sessionService;

    @Autowired
    public GlobalController(DiscordRestService discordRestService, SessionService sessionService) {
        this.discordRestService = discordRestService;
        this.sessionService = sessionService;
    }

    @ModelAttribute("avatarUrl")
    public String getTest() {
        if (sessionService.isUserAuthenticated()) {
            UserPOJO userPOJO = discordRestService.getAuthenticatedUserData();
            return discordCdnUrl + "avatars/" + userPOJO.getId() + "/" + userPOJO.getAvatar() + ".png";
        }
        return null;
    }
}
