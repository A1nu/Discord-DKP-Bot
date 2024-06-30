package ee.a1nu.discord_dkp_bot.database.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "guild_member")
public class GuildMember extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_guild", nullable = false)
    Guild guild;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member", nullable = false)
    Member member;

    @Column(name = "member_join_date", nullable = false)
    Date joinDate;

    @Column(name = "member_suspended")
    boolean suspended;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "guildMember")
    Set<EventAttendance> eventAttendance;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "member")
    Set<GuildMemberNote> guildMemberNotes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "owner")
    Set<PersonalizedItem> personalizedItems;
}
