package ru.nsu.regSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.regSystem.entities.User;
import ru.nsu.regSystem.services.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("errorMessage","неверный логин или пароль");
        return "login";
    }

    @GetMapping("/login-reset")
    public String loginGetEmail(Model model){
        return "login-reset";
    }

    @PostMapping("/login-reset")
    public String loginReset(Model model,  @RequestParam String email){
        if(userService.sendTempPass(email))
            model.addAttribute("resetMessage","на вашу почту был отправлен временный пароль");
        else
            model.addAttribute("errorMessage","почта введена неверно");
        return "login-reset";
    }

    @PostMapping("/recover-password")
    public String loginRecover(Model model, @RequestParam String password){
        if(userService.getTempPassword().equals(password))
            return "/new-password";
        else{
            model.addAttribute("errorMessage","временный пароль введен не верно");
            return "login-reset";
        }
    }

    @GetMapping("/new-password")
    public String loginNewPass(){
        return "new-password";
    }

    @PostMapping("/new-password")
    public String loginCreateNewPass(@RequestParam String password){
        userService.updatePassword(password);
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if(!userService.createUser(user)){
            model.addAttribute("errorMessage", "Пользователь с email : " + user.getEmail() + "уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("events", user.getEvents());
        return "user-info";
    }

}
