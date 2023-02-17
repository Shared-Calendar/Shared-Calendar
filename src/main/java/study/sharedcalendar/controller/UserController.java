package study.sharedcalendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.sharedcalendar.dto.User;
import study.sharedcalendar.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public void signUp(@RequestBody @Valid User user) {
        userService.signUp(user);
    }

    @GetMapping
    public void idCheck(
            @RequestParam
            @Pattern(message = "잘못된 아이디 형식입니다."
                    , regexp = "^[a-z0-9_-]{3,10}")
            String userId) {
        userService.userIdDuplicationCheck(userId);
    }
}
