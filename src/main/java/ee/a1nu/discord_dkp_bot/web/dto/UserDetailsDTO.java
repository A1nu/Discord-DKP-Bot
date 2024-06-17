package ee.a1nu.discord_dkp_bot.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailsDTO {
    Long id;
    String username;
    String avatar;
    String globalName;
}
