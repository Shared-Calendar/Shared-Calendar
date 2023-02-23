package study.sharedcalendar.dto;

import lombok.Getter;

@Getter
public class LoginRes {
    int id;
    String userId;
    String password;
    boolean activate;
    int tryCount;
}
