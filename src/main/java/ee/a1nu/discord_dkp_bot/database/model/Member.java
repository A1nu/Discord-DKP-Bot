package ee.a1nu.discord_dkp_bot.database.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "member")
@Getter
@Setter
public class Member extends BaseEntity {
    @Column(name = "member_snowflake", nullable = false)
    Long snowflake;

    @JoinColumn(name = "member_configuration")
    @OneToOne(fetch = FetchType.LAZY)
    MemberConfiguration memberConfiguration;
}
