package hibernate.dao.impl;

import hibernate.dao.ToursDao;
import hibernate.model.Package;
import hibernate.model.Tour;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ToursDaoImpl implements ToursDao {

    private final SessionFactory sessionFactory;

    public ToursDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Tour> findAll() {
        try(Session session = sessionFactory.openSession()){
            List<Tour> tourList = session.createQuery("from Tour", Tour.class).list();
            tourList.forEach(System.out::println);
            return  tourList;
        }
    }

    @Override
    public Tour getTourById(int id) {
        try(Session session = sessionFactory.openSession()){
            Tour tour = session.load(Tour.class, id);
            System.out.println("Obiekt o podanym id to: ----> " + tour);

            return tour;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteTour(int id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            Query query = session.createQuery("delete from Tour where id=: id");
            query.setParameter("id", id);

            query.executeUpdate();
            transaction.commit();

            System.out.println("Usunięto obiekt o podanym id z bazy :(");
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
    public List<Tour> getToursByPackageId(int packageId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Tour> query = session.createQuery("FROM Tour tour WHERE tour.packageId.id = :id", Tour.class);
            query.setParameter("id", packageId);
            List<Tour> tours = query.list();
            tours.forEach(System.out::println);
            return tours;
        }
    }

    @Override
    public Tour insertTour(Tour tour) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(tour);
            transaction.commit();

            System.out.println("Dodano podany obiekt do bazy :)");
            return tour;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }
}
