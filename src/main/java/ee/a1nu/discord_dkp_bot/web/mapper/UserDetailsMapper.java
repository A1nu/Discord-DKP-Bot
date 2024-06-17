package ee.a1nu.discord_dkp_bot.web.mapper;

import ee.a1nu.discord_dkp_bot.web.dto.UserDetailsDTO;

import java.util.Map;

public class UserDetailsMapper {

    public static UserDetailsDTO mapAttributesFromSession(Map<String, Object> attributes) {
        return UserDetailsDTO.builder()
                .id(Long.parseLong(attributes.get("id").toString()))
                .username(attributes.get("username").toString())
                .globalName(attributes.get("global_name").toString())
                .avatar(attributes.get("avatar").toString())
                .build();
    }
}
