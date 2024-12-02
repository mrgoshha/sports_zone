package ru.nsu.regSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nsu.regSystem.entities.SportsComplex;
import ru.nsu.regSystem.services.SportsComplexService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class SportsComplexesController {
    private final SportsComplexService sportsComplexService;

    @GetMapping("/admin/sports-complex")
    public String sportsComplexes(Model model, @PageableDefault(value=3, page=0, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("complexes", sportsComplexService.getSportsComplexesPage(pageable));
        return "complexes";
    }

    @GetMapping("admin/sports-complex/attendance")
    public String sportsEvent( Model model,
                               @RequestParam(name = "sportsComplex", required = false) SportsComplex complexFilter,
                               @RequestParam(name = "startDateAndTime", required = false) String startDateAndTime){
        List<Object[]> attendanceList = sportsComplexService.getAttendance(startDateAndTime, complexFilter);
        Map<String, Long> attendanceMap = new HashMap<>();
        for (Object[] row : attendanceList) {
            SportsComplex complex = (SportsComplex) row[0];
            long attendance = (long) row[1];
            attendanceMap.put(complex.getName(), attendance);
        }
        model.addAttribute("complexes", attendanceMap);
        model.addAttribute("complexesAll", sportsComplexService.getSportsComplexes());
        return "event-attendance";
    }

    @GetMapping("/admin/sports-complex/{id}")
    public String sportsComplexesInfo(@PathVariable Long id, Model model) {
        model.addAttribute("complex", sportsComplexService.getSportComplexById(id));
        return "complex-info";
    }

    @GetMapping("admin/sports-complex/create")
    public String createSportComplex() {
        return "create-complex";
    }

    @PostMapping("/admin/sports-complex/create")
    public String createSportComplex(SportsComplex sportsComplex) {
        sportsComplexService.saveComplex(sportsComplex);
        return "redirect:/admin/sports-complex";
    }

    @PostMapping("/admin/sports-complex/delete/{id}")
    public String deleteSportComplex(@PathVariable Long id) {
        sportsComplexService.deleteComplex(id);
        return "redirect:/admin/sports-complex";
    }

    @PostMapping("/admin/sports-complex/update/{id}")
    public String updateSportComplex(SportsComplex sportsComplex) {
        sportsComplexService.updateComplex(sportsComplex);
        return "redirect:/admin/sports-complex/{id}";//admin/sports-complex";
    }

    @GetMapping("/admin/sports-complex/update/{id}")
    public String updateComplex(@PathVariable Long id, Model model) {
        model.addAttribute("complex", sportsComplexService.getSportComplexById(id));
        return "complex-update";
    }
}
