package ee.a1nu.discord_dkp_bot.web.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class GuildDTO implements Serializable {
    String id;
    String name;
    String icon;
    Boolean owner;
    Integer permissions;
    Boolean botExists;
}
