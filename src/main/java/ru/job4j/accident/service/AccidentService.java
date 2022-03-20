package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;
import java.util.Collection;

@Service
public class AccidentService {
    private AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public Collection<AccidentType> getAccTypeFromRep() {
        return accidentMem.getAccType();
    }

    public Accident findByIdFromRep(int id) {
       return accidentMem.findByID(id);
    }

    public Collection<Rule> getRulesFromRep() {
        return accidentMem.getRules();
    }

    public Accident add(Accident accident, String[] ids) {
       accident.setType(accidentMem.findTypeByID(accident.getType().getId()));
       accident.setRules(accidentMem.getRulesByID(ids));
       accidentMem.addAcc(accident);
       return accidentMem.addAcc(accident);
    }

    public Collection<Accident> allAccident() {
        return  accidentMem.getAccidents();
    }
}
