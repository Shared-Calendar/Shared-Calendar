package study.sharedcalendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.sharedcalendar.dto.UserDTO;
import study.sharedcalendar.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/sign-up")
    public void signUp(@RequestBody @Validated UserDTO user) {
        //if (errors.hasErrors()) {
        //    throw new ValidationException("INVALID");
        //}
        userService.signUp(user);
    }

    @PostMapping("/sign-in")
    public void signIn(@RequestBody UserDTO user) {
        userService.signIn(user);
    }
}
