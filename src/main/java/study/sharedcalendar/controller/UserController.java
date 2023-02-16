package study.sharedcalendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.sharedcalendar.dto.User;
import study.sharedcalendar.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public void signUp(@Valid @RequestBody User user) {
        userService.signUp(user);
    }

    @GetMapping
    public void idCheck(@RequestParam String userId) {
        userService.userIdDuplicationCheck(userId);
    }
}
