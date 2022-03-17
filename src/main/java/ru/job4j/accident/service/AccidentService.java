package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;
import java.util.List;

@Service
public class AccidentService {
    private AccidentMem accidentMem = new AccidentMem();

    public Accident add(Accident accident) {
       return accidentMem.addAcc(accident);
    }

    public Collection<Accident> allAccident() {
        return  accidentMem.getAccidents();
    }
}
