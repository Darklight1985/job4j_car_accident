package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import java.util.Collection;
import java.util.Set;

@Service
public class AccidentService implements ServiceAcc {
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
        Set<Rule> ruleSet = accidentJdbc.getRulesByID(ids);
        accident.setRules(ruleSet);
        if (accident.getId() == 0) {
            accident = accidentJdbc.save(accident);
        } else {
           accident = accidentJdbc.update(accident);
        }
        for (Rule rule: ruleSet) {
            accidentJdbc.addRuleToAcc(accident.getId(), rule.getId());
        }
        return accident;
    }

    public Collection<Accident> allAccident() {
        return accidentJdbc.getAll();
    }
}
