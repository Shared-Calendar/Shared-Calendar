package study.sharedcalendar.dto;

import lombok.*;
import javax.validation.constraints.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z[0-9]]{5,15}$", message = "영/숫자로만 이루어진 5-15글자")
    private String userId;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$", message = "영/숫자/특수문자를 모두 사용한 8-20글자")
    private String password;

    @NotBlank
    @Email(message = "이메일 형식")
    private String email;

    private String inviteUrl;

    private boolean activate;
    private int loginCount;
}

