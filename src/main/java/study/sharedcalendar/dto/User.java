package study.sharedcalendar.dto;

import lombok.Getter;

@Getter
public class User {
    private int id;
    private String userId;
    private String password;
    private String email;
    private String inviteUrl;
}
