package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident, String[] ids) {
       return this.tx(session -> {
           for (String id: ids) {
               accident.addRule(session.find(Rule.class, Integer.parseInt(id)));
           }
            session.saveOrUpdate(accident);
            return accident;
        });
    }

    public Accident findByID(int id) {
        return this.tx(session -> {
            final Query<Accident> query = session.createQuery("select distinct a from Accident a "
                    + "join fetch  a.type t "
                    + "join fetch  a.rules "
                    + "where a.id = :value", Accident.class
            );
            query.setParameter("value", id);
            return query.uniqueResult();
        });
    }

    public AccidentType findTypeByID(int id) {
        return (AccidentType) this.tx(session -> {
            final Query query = session.createQuery("from AccidentType where id = :value");
            query.setParameter("value", id);
            return query.uniqueResult();
        });
    }

    public Rule findRuleByID(int id) {
        return (Rule) this.tx(session -> {
            final Query query = session.createQuery("from Rule where id = :value");
            query.setParameter("value", id);
            return query.uniqueResult();
        });
    }

    public Set<Rule> getRulesByID(String[] ids) {
        Set<Rule> result = new HashSet<>();
        for (String str: ids) {
            result.add(findRuleByID(Integer.parseInt(str)));
        }
        return result;
    }

    public Collection<Accident> getAll() {
        return this.tx(session -> {
            final Query query = session.createQuery("select distinct a from Accident a "
                    + "join fetch a.type t "
                    + "join fetch a.rules", Accident.class
            );
            return query.getResultList();
        });
    }

    public Collection<Rule> getRules() {
        return this.tx(session -> {
            final Query query = session.createQuery("from Rule ");
            return query.getResultList();
        });
    }

    public Collection<AccidentType> getAccType() {
        return this.tx(session -> {
            final Query query = session.createQuery("from AccidentType ");
            return query.getResultList();
        });
    }

    public Collection<Accident> getAccidentsByType(int typeId) {
        return this.tx(session -> {
            final Query query = session.createQuery("from Accident where Accident.id = :value");
            query.setParameter("value", typeId);
            return query.getResultList();
        });
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
