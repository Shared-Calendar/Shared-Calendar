package study.sharedcalendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.sharedcalendar.dto.UserDTO;
import study.sharedcalendar.service.UserService;
import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid UserDTO user, Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException("Valid_Error");
        }
        userService.signUp(user);
    }
}
