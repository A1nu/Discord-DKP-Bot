package ee.a1nu.discord_dkp_bot.web.controller;


import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Guild;
import ee.a1nu.discord_dkp_bot.bot.service.DiscordBotService;
import ee.a1nu.discord_dkp_bot.database.model.Encounter;
import ee.a1nu.discord_dkp_bot.web.dto.GuildDTO;
import ee.a1nu.discord_dkp_bot.web.rest.DiscordRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Controller
public class DashboardController {
    @Value("${discord.bot.clientId}")
    private String clientId;
    @Value("${discord.bot.permissions}")
    private String permissions;

    final DiscordRestService discordRestService;
    private final DiscordBotService discordBotService;

    @Autowired
    public DashboardController(DiscordRestService discordRestService, DiscordBotService discordBotService) {
        this.discordRestService = discordRestService;
        this.discordBotService = discordBotService;
    }

    @GetMapping(value = "/dashboard")
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView();
        List<GuildDTO> guildList = discordRestService.getUserGuilds();
        List<Guild> botGuildList = discordBotService.getBotGuilds();
        List<GuildDTO> listOfGuildsWhereUserAndBotExists = guildList.stream()
                .peek(guildDTO -> guildDTO.setBotExists(discordBotService.isBotMemberOfAGuild(Snowflake.of(guildDTO.getId()))))
                .filter(guildDTO -> guildDTO.getBotExists() || guildDTO.getOwner() || guildDTO.getPermissions() == 2147483647)
                .sorted(Comparator.comparing(GuildDTO::getName).thenComparing(GuildDTO::getOwner).reversed().thenComparing(GuildDTO::getBotExists)).toList();
        List<GuildDTO> displayGuilds = Stream.concat(
                listOfGuildsWhereUserAndBotExists.stream().filter(GuildDTO::getBotExists).sorted(Comparator.comparing(GuildDTO::getName)),
                listOfGuildsWhereUserAndBotExists.stream().filter(guildDTO -> !guildDTO.getBotExists()).sorted(Comparator.comparing(GuildDTO::getName))
        ).toList();
        modelAndView.addObject("botGuilds", displayGuilds);
        modelAndView.addObject("client_id", clientId);
        modelAndView.addObject("permissions", permissions);
        modelAndView.setViewName("view/dashboard");
        return modelAndView;
    }
}
