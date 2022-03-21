package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import java.util.Collection;

@Service
public class AccidentService {
    private AccidentJdbcTemplate accidentJdbc;

    public AccidentService(AccidentJdbcTemplate accidentJdbc) {
        this.accidentJdbc = accidentJdbc;
    }

    public Collection<AccidentType> getAccTypeFromRep() {
        return accidentJdbc.getAccType();
    }

    public Accident findByIdFromRep(int id) {
       return accidentJdbc.findByID(id);
    }

    public Collection<Rule> getRulesFromRep() {
        return accidentJdbc.getRules();
    }

    public Accident add(Accident accident, String[] ids) {
        accident.setType(accidentJdbc.findTypeByID(accident.getType().getId()));
        accident.setRules(accidentJdbc.getRulesByID(ids));
        return accidentJdbc.save(accident);
    }

    public Collection<Accident> allAccident() {
        return accidentJdbc.getAll();
    }
}
