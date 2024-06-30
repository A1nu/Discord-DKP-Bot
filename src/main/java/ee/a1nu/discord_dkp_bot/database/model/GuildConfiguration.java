package ee.a1nu.discord_dkp_bot.database.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "guild_configuration")
@Getter
@Setter
public class GuildConfiguration extends BaseEntity {
    @JoinColumn(name = "guild_id", nullable = false)
    @OneToOne
    Guild guild;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "guildConfiguration")
    Set<GuildAdministratorRole> administratorRoles;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "guildConfiguration")
    Set<GuildMemberRole> memberRoles;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "guildConfiguration")
    Set<GuildModeratorRole> moderatorRoles;

    @Column(name = "stash_enabled")
    boolean stashEnabled;

    @Column(name = "attendance_enabled")
    boolean attendanceEnabled;

    @Column(name = "loot_pretending_days_delay")
    int lootPretendingDaysDelay;
}
