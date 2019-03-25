package hibernate.dao.impl;

import hibernate.dao.StatesDao;
import hibernate.model.State;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StatesDaoImpl implements StatesDao {

    private final EntityManager em;

    public StatesDaoImpl(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public List<State> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<State> cq = cb.createQuery(State.class);
        Root<State> rootEntry = cq.from(State.class);
        CriteriaQuery<State> all = cq.select(rootEntry); // tutaj dodajemy warunki jako .where
        TypedQuery<State> allQuery = em.createQuery(all);

        List<State> resultList = allQuery.getResultList();
        resultList.forEach(e -> System.out.println(e));
        return resultList;
    }

    @Override
    public State getStateById(String id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<State> cq = cb.createQuery(State.class);
        Root<State> root = cq.from(State.class);
        CriteriaQuery<State> all = cq.select(root)
                .where(cb.equal(root.get("stateId"), id)); // tutaj dodajemy warunki jako .where
        TypedQuery<State> allQuery = em.createQuery(all);

        State singleResult = allQuery.getSingleResult();
        System.out.println(singleResult);

        return singleResult;
        //return em.find(State.class, id);
    }

    @Override
    public boolean insertState(State state) {
        State state1 = em.find(State.class, state.getStateId());
        if (state1 != null){
            System.out.println("Obiekt o takim id już istnieje");
            return false;
        }
        em.getTransaction().begin();
        em.persist(state);
        em.getTransaction().commit();

        System.out.println("Dodano podany obiekt :)");
        return true;
    }

    @Override
    public boolean deleteState(String id) {
        State state = em.find(State.class, id);
        if(state == null){
            System.out.println("Nie ma obiektu o takim id");
            return false;
        }
        em.getTransaction().begin();
        em.remove(state);
        em.getTransaction().commit();

        System.out.println("Obiekt został usunięty :)");
        return true;
    }
}