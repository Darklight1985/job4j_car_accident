package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Set;

@Controller
public class AccidentControl {
    private final AccidentMem accidents;

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidents.getAccType());
        model.addAttribute("rules", accidents.getRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        accident.setType(accidents.findTypeByID(accident.getType().getId()));
        String[] ids = req.getParameterValues("rIds");
        Collection<Rule> rules = accidents.getRulesByID(ids);
        accident.setRules((Set<Rule>) rules);
        accidents.addAcc(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", accidents.getAccType());
        model.addAttribute("rules", accidents.getRules());
        model.addAttribute("accident", accidents.finfByID(id));
        return "accident/update";
    }
}
