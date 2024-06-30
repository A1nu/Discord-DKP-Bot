package ee.a1nu.discord_dkp_bot.web.controller;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Guild;
import ee.a1nu.discord_dkp_bot.bot.service.DiscordBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired
    private final DiscordBotService discordBotService;

    public AdminController(DiscordBotService discordBotService) {
        this.discordBotService = discordBotService;
    }

    @GetMapping(value = "/guild/{guildId}/settings")
    public ModelAndView getAdminPanel(@PathVariable String guildId) {
        ModelAndView modelAndView = new ModelAndView();
        Guild guild = discordBotService.getGuildById(Snowflake.of(guildId));
        modelAndView.addObject("guildName", guild.getName());
        modelAndView.addObject("currentView", "settings");
        modelAndView.setViewName("view/admin");
        return modelAndView;
    }
}
