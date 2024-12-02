package ru.nsu.regSystem.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.nsu.regSystem.entities.User;


@Controller
public class MainController {

    @GetMapping("/")
    public String greeting(@AuthenticationPrincipal User user, Model model) {
        if (user != null) {
            model.addAttribute("auth", true);
            model.addAttribute("admin", user.getAuthorities().toString().equals("[ROLE_ADMIN]"));
        }
        return "redirect:/sports-events";
    }


}

