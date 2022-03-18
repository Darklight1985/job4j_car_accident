package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents = new HashMap<>();
    private AtomicInteger number = new AtomicInteger();

    public AccidentMem() {
        addAcc(new Accident(1, "Нарушение 1",
                "какое-то нарушение", "Москва, ул. Иванова д.2",
                AccidentType.of(1, "Две машины")));
        addAcc(new Accident(2, "Нарушение 2",
                "какое-то нарушение", "C-Петербург, ул. Петрова д.5",
                AccidentType.of(1, "Две машины")));
        addAcc(new Accident(3, "Нарушение 3",
                "какое-то нарушение", "Екатеринбург, ул. Сидорова д.10",
                AccidentType.of(1, "Две машины")));
    }

    public Accident addAcc(Accident accident) {
        return accidents.put(number.incrementAndGet(), accident);
    }

    public Accident finfByID(int id) {
        return accidents.get(id);
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public Collection<Accident> getAccidentsByType(int typeId) {
        Collection<Accident> list = accidents.values();
        Collection<Accident> result = new ArrayList<>();
        for (Accident accident: list) {
            if (accident.getType().getId() == typeId) {
                result.add(accident);
            }
        }
        return result;
    }
}
