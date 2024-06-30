package ee.a1nu.discord_dkp_bot.database.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "guild")
public class Guild extends BaseEntity {
    @Column(name = "guild_snowflake" ,nullable = false)
    Long snowflake;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "guild", orphanRemoval = true)
    Set<GuildMember> guildMembers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "guild", orphanRemoval = true)
    Set<GuildEvent> guildEvents;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "guild_configuration_id")
    GuildConfiguration guildConfiguration;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "guild", orphanRemoval = true)
    Set<PersonalizedItem> guildItems;
}
