package hibernate.querysRun;

import hibernate.dao.impl.StatesDaoImpl;
import hibernate.model.State;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StateDaoRun {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        StatesDaoImpl statesDao = new StatesDaoImpl(entityManager);

        //findingAll
        statesDao.findAll();


        //deleteStateByID
        statesDao.deleteState("WP");


        //getById
        statesDao.getStateById("WA");


        //insertState
        State state = new State();
        state.setStateId("WP");
        state.setStateName("Wielkopolska");
        statesDao.insertState(state);


        entityManager.close();
        entityManagerFactory.close();
    }
}
