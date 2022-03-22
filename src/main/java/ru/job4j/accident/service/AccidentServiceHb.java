package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.Collection;

@Service
public class AccidentServiceHb implements ServiceAcc {
    private AccidentHibernate accidentHibernate;

    public AccidentServiceHb(AccidentHibernate accidentJdbc) {
        this.accidentHibernate = accidentJdbc;
    }

    public Collection<AccidentType> getAccTypeFromRep() {
        return accidentHibernate.getAccType();
    }

    public Accident findByIdFromRep(int id) {
       return accidentHibernate.findByID(id);
    }

    public Collection<Rule> getRulesFromRep() {
        return accidentHibernate.getRules();
    }

    public Accident add(Accident accident, String[] ids) {
        accident.setType(accidentHibernate.findTypeByID(accident.getType().getId()));
        accident = accidentHibernate.save(accident, ids);
        return accident;
    }

    public Collection<Accident> allAccident() {
        return accidentHibernate.getAll();
    }
}
