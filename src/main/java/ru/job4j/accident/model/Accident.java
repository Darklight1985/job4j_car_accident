package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "accident")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "description")
    private String text;
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acc_type_id")
    private AccidentType type;

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(name = "accident_rule", joinColumns = {
            @JoinColumn(name = "rule_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
            @JoinColumn(name = "accident_id", nullable = false, updatable = false)})
    private Set<Rule> rules = new HashSet<>();

    public Accident() {
    }

    public Accident(int id, String name, String text, String address, AccidentType type) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
        this.type = type;
    }

    public AccidentType getType() {
        return type;
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    public void setType(AccidentType type) {
        this.type = type;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id && Objects.equals(name, accident.name)
                && Objects.equals(text, accident.text) && Objects.equals(address, accident.address)
                && Objects.equals(type, accident.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, text, address, type);
    }

    @Override
    public String toString() {
        return "Accident{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", text='" + text + '\''
                + ", address='" + address + '\''
                + ", type=" + type
                + '}';
    }
}
