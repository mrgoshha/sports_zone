package ru.nsu.regSystem.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nsu.regSystem.services.AdminService;
import ru.nsu.regSystem.services.UserService;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final AdminService adminService;

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/request")
    public String request(Model model, @PageableDefault(value=3, page=0, sort = { "dateOfCreated" }, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("requests", adminService.getAllRequest(pageable));
        return "request-registration";
    }

    @GetMapping("/admin/request/{id}")
    public String sportsEventInfo(@PathVariable Long id, Model model) {
        model.addAttribute("request", adminService.getRequestById(id));
        if (adminService.getRequestById(id).isStatus()) {
            model.addAttribute("approveMessage", "заявка одобрена");
        }
        return "request-info";
    }

    @PostMapping("/admin/request/delete/{id}")
    public String deleteRequest(@PathVariable Long id) {
        adminService.deleteRequestEvent(id);
        return "redirect:/admin/request";
    }

    @PostMapping("/admin/request/approve/{id}")
    public String approveRequest(@PathVariable Long id, Model model) {
        model.addAttribute("request", adminService.getRequestById(id));
        if (!adminService.registrationUserEvent(id))
            model.addAttribute("errorMessage", "пользователь уже зарегестрирован");
        else
            model.addAttribute("approveMessage", "заявка одобрена");
        return "request-info";
    }


}
