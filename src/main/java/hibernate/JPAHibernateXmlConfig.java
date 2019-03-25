package hibernate;

import hibernate.dao.StatesDao;
import hibernate.dao.impl.StatesDaoImpl;
import hibernate.model.State;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAHibernateXmlConfig {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = entityManagerFactory.createEntityManager();

        //<STATES>
        StatesDao statesDao = new StatesDaoImpl(em);


        State state = new State();
        state.setStateId("MP");
        state.setStateName("Malopolska");

        statesDao.insertState(state);
        statesDao.deleteState("MP");
        statesDao.findAll().forEach(System.out::println);
        //</STATES>



        em.close();
        entityManagerFactory.close();
    }

}
