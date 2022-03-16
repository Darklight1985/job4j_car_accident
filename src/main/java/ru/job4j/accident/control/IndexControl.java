package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        AccidentMem accidentMem = new AccidentMem();
        accidentMem.addAcc(new Accident(1, "Нарушение 1",
                "какое-то нарушение", "Москва, ул. Иванова д.2"));
        accidentMem.addAcc(new Accident(2, "Нарушение 2",
                "какое-то нарушение", "C-Петербург, ул. Петрова д.5"));
        accidentMem.addAcc(new Accident(3, "Нарушение 3",
                "какое-то нарушение", "Екатеринбург, ул. Сидорова д.10"));

        HashMap<Integer, Accident> integerAccidentHashMap = accidentMem.getAccidents();
        model.addAttribute("accidents", integerAccidentHashMap);
        return "index";
    }
}
