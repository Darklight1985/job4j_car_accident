package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> list = new ArrayList<>();
        list.add("Vasya");
        list.add("Masha");
        list.add("Nikita");
        model.addAttribute("names", list);
        model.addAttribute("user", "Мой юзер");
        return "index";
    }
}
