package hibernate.dao.impl;

import hibernate.dao.ExplorerDao;
import hibernate.model.Explorer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ExplorerDaoImpl implements ExplorerDao {

    private final SessionFactory sessionFactory;

    public ExplorerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Explorer> findAll() {
        try (Session session = sessionFactory.openSession()) {
            List<Explorer> explorersList = session.createQuery("from Explorer", Explorer.class).list();
            explorersList.forEach(e -> System.out.println(e));

            return explorersList;
        }
    }

    @Override
    public Explorer getExplorerById(int id) {
        try(Session session =sessionFactory.openSession()){
            Explorer explorer = session.load(Explorer.class, id);
            System.out.println("Obiekt o podanym id to: -----> " + explorer);

            return explorer;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insertExplorer(Explorer explorer) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.save(explorer);
            transaction.commit();

            System.out.println("Dodano podany obiekt do bazy :)");
            return true;
        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteExplorer(int id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            Query query = session.createQuery("delete from Explorer where id=: id");
            query.setParameter("id", id);

            query.executeUpdate();
            transaction.commit();

            System.out.println("Usunięto obiekt o podanym id z bazy :(");
            return true;
        }catch(Exception e){
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Explorer> getExplorersByStateId(String stateId) {
        try(Session session = sessionFactory.openSession()){
            Query<Explorer> query = session.createQuery("from Explorer explorer where explorer.stateID.stateId= :id", Explorer.class);
            query.setParameter("id", stateId);

            List<Explorer> explorerList = query.list();
            explorerList.forEach(System.out::println);
            return explorerList;
        }
    }
}
