package study.sharedcalendar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private String userId;
    private String password;
    private String email;
    private String inviteUrl;
}
