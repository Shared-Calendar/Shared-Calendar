package study.sharedcalendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.sharedcalendar.dto.User;
import study.sharedcalendar.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public void signUp(@RequestBody User user) {
        userService.signUp(user);
    }

    @GetMapping
    public void idCheck(@RequestParam String userId) {
        userService.idCheck(userId);
    }
}
