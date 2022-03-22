package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentServiceHb;
import ru.job4j.accident.service.ServiceAcc;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {
    private final ServiceAcc accidents;

    public AccidentControl(AccidentServiceHb accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidents.getAccTypeFromRep());
        model.addAttribute("rules", accidents.getRulesFromRep());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        accidents.add(accident, ids);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", accidents.getAccTypeFromRep());
        model.addAttribute("rules", accidents.getRulesFromRep());
        model.addAttribute("accident", accidents.findByIdFromRep(id));
        return "accident/update";
    }
}
