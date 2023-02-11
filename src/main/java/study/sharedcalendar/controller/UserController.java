package study.sharedcalendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.sharedcalendar.dto.User;
import study.sharedcalendar.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public String signUp(@RequestBody User user) {
        userService.signUp(user);
        return "ok";
    }

    @GetMapping("")
    public String idCheck(@RequestParam String userId) {
        int result = userService.idCheck(userId);
        if( result == 0 ) return "ok";
        else return "존재하는 아이디입니다.";
    }


}
