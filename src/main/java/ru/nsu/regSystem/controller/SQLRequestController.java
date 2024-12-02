package ru.nsu.regSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.regSystem.services.SQLRequestService;


@Controller
@RequiredArgsConstructor
public class SQLRequestController {
    private final SQLRequestService sqlRequestService;

    @GetMapping("/admin/query")
    public String executeQuery(Model model, @RequestParam(name = "sql", required = false) String sql)  {
        if (sql != null) {
            try {
                model.addAttribute("request", sqlRequestService.createRequest(sql));
                model.addAttribute("sql", sql);
            } catch (Exception e){
                model.addAttribute("exception", e);
            }

        }

        return "query";
    }
}
