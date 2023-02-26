package study.sharedcalendar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    int id;
    String userId;
    String password;
    boolean activate;
    int tryCount;
}
