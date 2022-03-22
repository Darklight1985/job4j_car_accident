package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

public interface ServiceAcc {

    Collection<AccidentType> getAccTypeFromRep();

    Accident findByIdFromRep(int id);

     Collection<Rule> getRulesFromRep();

     Accident add(Accident accident, String[] ids);

     Collection<Accident> allAccident();
}
