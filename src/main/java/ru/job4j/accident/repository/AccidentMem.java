package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents = new HashMap<>();
    private int number = 0;

    public Accident addAcc(Accident accident) {
        return accidents.put(number++, accident);
    }

    public HashMap<Integer, Accident> getAccidents() {
        return accidents;
    }
}
