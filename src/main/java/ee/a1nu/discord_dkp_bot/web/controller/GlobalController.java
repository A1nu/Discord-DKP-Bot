package ee.a1nu.discord_dkp_bot.web.controller;

import ee.a1nu.discord_dkp_bot.web.dto.UserDetailsDTO;
import ee.a1nu.discord_dkp_bot.web.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice
public class GlobalController {
    @Value("${discord.cdnUrl}")
    private String discordCdnUrl;

    private final SessionService sessionService;

    @Autowired
    public GlobalController( SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @ModelAttribute("avatarUrl")
    public String getAvatarUrl() {
        if (sessionService.isUserAuthenticated()) {
            UserDetailsDTO userDetailsDTO = sessionService.getUserDetails();
            return discordCdnUrl + "avatars/" + userDetailsDTO.getId() + "/" + userDetailsDTO.getAvatar() + ".png";
        }
        return null;
    }
}
