package study.sharedcalendar.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private int id;
    private String userId;
    private String password;
    private String email;
    private String inviteUrl;
}
