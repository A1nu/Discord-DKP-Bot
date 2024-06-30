package ee.a1nu.discord_dkp_bot.bot.service;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.Guild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscordBotService {
    GatewayDiscordClient gatewayDiscordClient;

    @Autowired
    public DiscordBotService(GatewayDiscordClient gatewayDiscordClient) {
        this.gatewayDiscordClient = gatewayDiscordClient;
    }

    public boolean isBotMemberOfAGuild(Snowflake guildSnowflake) {
        List<Guild> guilds = gatewayDiscordClient.getGuilds().collectList().block();
        assert guilds != null;
        if (!guilds.isEmpty()) {
           return guilds.stream().anyMatch(guild -> guild.getId().equals(guildSnowflake));
        }
        return false;
    }

    public List<Guild> getBotGuilds() {
        return gatewayDiscordClient.getGuilds().collectList().block();
    }

    public Guild getGuildById(Snowflake guildSnowflake) {
        return gatewayDiscordClient.getGuildById(guildSnowflake).block();
    }
}
