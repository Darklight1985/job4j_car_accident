package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents;
    private AtomicInteger number;

    public AccidentMem() {
        this.accidents = new HashMap<>();
        this.number = new AtomicInteger();
        addAcc(new Accident(1, "Нарушение 1",
                "какое-то нарушение", "Москва, ул. Иванова д.2"));
        addAcc(new Accident(2, "Нарушение 2",
                "какое-то нарушение", "C-Петербург, ул. Петрова д.5"));
        addAcc(new Accident(3, "Нарушение 3",
                "какое-то нарушение", "Екатеринбург, ул. Сидорова д.10"));
    }

    public Accident addAcc(Accident accident) {
        return accidents.put(number.incrementAndGet(), accident);
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }
}
