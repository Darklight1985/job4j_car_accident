package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccTypeRepository;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.*;

@Service
public class AccidentServiceData implements ServiceAcc {
    private AccidentRepository accidentRepository;
    private AccTypeRepository accTypeRepository;
    private RuleRepository ruleRepository;

    public AccidentServiceData(AccidentRepository accidentRepository,
                               AccTypeRepository accTypeRepository,
                               RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.accTypeRepository = accTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    public Collection<AccidentType> getAccTypeFromRep() {
        List<AccidentType> res = new ArrayList<>();
        accTypeRepository.findAll().forEach(res::add);
        return res;
    }

    public Accident findByIdFromRep(int id) {
        return accidentRepository.findById(id).get();
    }

    public Collection<Rule> getRulesFromRep() {
        List<Rule> res = new ArrayList<>();
        ruleRepository.findAll().forEach(res::add);
        return res;
    }

    public Accident add(Accident accident, String[] ids) {
        accident.setType(accTypeRepository.findById(accident.getType().getId()).get());
        Set<Rule> set = new HashSet<>();
        for (String id: ids) {
            set.add(ruleRepository.findById(Integer.parseInt(id)).get());
        }
        accident.setRules(set);
        accident = accidentRepository.save(accident);
        return accident;
    }

    public Collection<Accident> allAccident() {
        List<Accident> res = new ArrayList<>();
        accidentRepository.findAll().forEach(res::add);
        return res;
    }
}
