package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
        jdbc.update("insert into accident (name, description, address, acc_type_id) "
                        + "values (?, ?, ?, ?)",
                accident.getName(), accident.getText(), accident.getAddress(), accident.getType());
        return accident;
    }

    public Accident findByID(int id) {
        return jdbc.queryForObject(
                "select id, name, description, address, acc_type_id from accident where id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(id);
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("description"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(findTypeByID(rs.getInt("acc_type_id")));
                    return accident;
                },
                id);
    }

    public AccidentType findTypeByID(int id) {
        return jdbc.queryForObject(
                "select id, name from acc_type where id = ?",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(id);
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                },
                id);
    }

    public Rule findRuleByID(int id) {
        return jdbc.queryForObject(
                "select id, name from rule where id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(id);
                    rule.setName(rs.getString("name"));
                    return rule;
                },
                id);
    }

    public Set<Rule> getRulesByID(String[] ids) {
        Set<Rule> result = new HashSet<>();
        for (String str: ids) {
            result.add(findRuleByID(Integer.parseInt(str)));
        }
        return result;
    }

    public List<Accident> getAll() {
        return jdbc.query("select id, name, description, address, acc_type_id from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("description"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(findTypeByID(rs.getInt("acc_type_id")));
                    return accident;
                });
    }

    public Collection<Rule> getRules() {
        return jdbc.query("select id, name from rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public Collection<AccidentType> getAccType() {
        return jdbc.query("select id, name from acc_type",
                (rs, row) -> {
                    AccidentType accType = new AccidentType();
                    accType.setId(rs.getInt("id"));
                    accType.setName(rs.getString("name"));
                    return accType;
                });
    }

    public Collection<Accident> getAccidentsByType(int typeId) {
        return getAll()
                .stream()
                .filter(e -> e
                        .getType()
                        .getId() == typeId)
                .collect(Collectors.toList());
    }
}
