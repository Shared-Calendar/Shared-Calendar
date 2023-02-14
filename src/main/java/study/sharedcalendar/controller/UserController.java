package study.sharedcalendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.constant.ErrorCode;
import study.sharedcalendar.dto.User;
import study.sharedcalendar.exception.DuplicateException;
import study.sharedcalendar.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public void signUp(@RequestBody User user) {
        System.out.println(user.getUserId());
        userService.signUp(user);
    }

    @GetMapping
    public void idCheck(@RequestParam String userId) {
        if (userService.idExist(userId))
            throw new DuplicateException(ErrorCode.ID_DUPLICATE);
    }
}
