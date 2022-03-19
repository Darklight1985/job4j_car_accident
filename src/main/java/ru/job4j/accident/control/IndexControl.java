package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;

import java.util.Collection;

@Controller
public class IndexControl {
    private AccidentService service;

    public IndexControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        Collection<Accident> list = service.allAccident();
        model.addAttribute("accidents", list);
        return "index";
    }
}
