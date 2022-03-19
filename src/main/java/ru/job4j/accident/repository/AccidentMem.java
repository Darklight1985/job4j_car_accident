package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents = new HashMap<>();
    private HashMap<Integer, AccidentType> accType = new HashMap<>();
    private AtomicInteger number = new AtomicInteger();

    public AccidentMem() {
        accType.put(1, AccidentType.of(1, "Две машины"));
        accType.put(2, AccidentType.of(2, "Машина и человек"));
        accType.put(3, AccidentType.of(3, "Машина и велосипед"));
        addAcc(new Accident(1, "Нарушение 1",
                "какое-то нарушение", "Москва, ул. Иванова д.2",
                accType.get(1)));
        addAcc(new Accident(2, "Нарушение 2",
                "какое-то нарушение", "C-Петербург, ул. Петрова д.5",
                accType.get(2)));
        addAcc(new Accident(3, "Нарушение 3",
                "какое-то нарушение", "Екатеринбург, ул. Сидорова д.10",
                accType.get(3)));
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
       return accidents.values()
               .stream()
               .filter(e -> e
                       .getType()
                       .getId() == typeId)
               .collect(Collectors.toList());
    }
}
