package ru.nsu.regSystem.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.regSystem.entities.SportsComplex;
import ru.nsu.regSystem.entities.SportsEvent;
import ru.nsu.regSystem.entities.Trainers;
import ru.nsu.regSystem.entities.User;
import ru.nsu.regSystem.services.SQLRequestService;
import ru.nsu.regSystem.services.SportsComplexService;
import ru.nsu.regSystem.services.SportsEventService;

@Controller
@RequiredArgsConstructor
public class SportsEventController {
    private final SportsEventService sportsEventService;
    private final SportsComplexService sportsComplexService;


    @GetMapping("/sports-events")
    public String sportsEvent(@AuthenticationPrincipal User user, Model model,
                              @PageableDefault(value=3, page=0, sort = { "startDateAndTime" }, direction = Sort.Direction.DESC) Pageable pageable,
                              @RequestParam(name = "name", required = false) String name,
                              @RequestParam(name = "sportsComplex", required = false) SportsComplex complex,
                              @RequestParam(name = "trainer", required = false)Trainers trainer,
                              @RequestParam(name = "price", required = false) Integer price){
        model.addAttribute("events", sportsEventService.filterEvent(name, complex, trainer, price, pageable));
        model.addAttribute("complexes",sportsComplexService.getSportsComplexes());
        model.addAttribute("trainers", sportsEventService.getTrainers());

        model.addAttribute("complex", complex);
        model.addAttribute("name", name);
        model.addAttribute("trainer", trainer);
        model.addAttribute("price", price);

        if(user != null){
            model.addAttribute("auth", true);
            model.addAttribute("admin", user.getAuthorities().toString().equals("[ROLE_ADMIN]"));
        }
        return "events";
    }



    @GetMapping("/sports-event/{id}")
    public String sportsEventInfo(@AuthenticationPrincipal User user,
                                  @PathVariable Long id, Model model) {
        model.addAttribute("event", sportsEventService.getSportsEventById(id));
        if(user != null){
            model.addAttribute("auth", true);
            model.addAttribute("admin", user.getAuthorities().toString().equals("[ROLE_ADMIN]"));
        }
        return "event-info";
    }

    @GetMapping("/admin/sports-event/create")
    public String createSportsEvent(Model model) {
        model.addAttribute("trainers", sportsEventService.getTrainers());
        model.addAttribute("complexes",sportsComplexService.getSportsComplexes());
        return "create-event";
    }

    @PostMapping("/admin/sports-event/create")
    public String createSportsEvent(SportsEvent sportsEvent) {
         sportsEventService.saveEvent(sportsEvent);
        return "redirect:/sports-events";
    }

    @PostMapping("/admin/sports-event/delete/{id}")
    public String deleteSportEvent(@PathVariable Long id) {
        sportsEventService.deleteEvent(id);
        return "redirect:/sports-events";
    }

    @PostMapping("/admin/sports-event/update/{id}")
    public String updateSportComplex(SportsEvent sportsEvent) {
        sportsEventService.updateEvent(sportsEvent);
        return "redirect:/sports-event/{id}";
    }

    @GetMapping("/admin/sports-event/update/{id}")
    public String updateComplex(@PathVariable Long id, Model model) {
        model.addAttribute("event", sportsEventService.getSportsEventById(id));
        model.addAttribute("complexes",sportsComplexService.getSportsComplexes());
        model.addAttribute("trainers", sportsEventService.getTrainers());
        return "event-update";
    }

    @PostMapping("/registration-event/{id}")
    public String registrationEvent(@AuthenticationPrincipal User user,
                                    @PathVariable Long id, Model model) {
        boolean checkCountSeats = sportsEventService.registrationUser(user, id);
        model.addAttribute("admin", user.getAuthorities().toString().equals("[ROLE_ADMIN]"));
        model.addAttribute("event", sportsEventService.getSportsEventById(id));
        if(!checkCountSeats)
            model.addAttribute("errorMessage", "к сожалению вы не можете зарегестрироваться на мероприятие");
        return "registration-event";
    }
}
