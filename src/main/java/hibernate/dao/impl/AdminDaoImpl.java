package hibernate.dao.impl;

import hibernate.dao.AdminDao;
import hibernate.model.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AdminDaoImpl implements AdminDao {

    private final SessionFactory sessionFactory;

    public AdminDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Admin> findAll() {
        try (Session session = sessionFactory.openSession()) {
            List<Admin> adminList = session.createQuery("from Admin", Admin.class).list();
            adminList.forEach(System.out::println);
            return adminList;
        }
    }

    @Override
    public Admin getAdminById(int id) {
        //Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            //transaction = session.beginTransaction();
            Admin admin = session.load(Admin.class, id);
            System.out.println("Obiekt o takim id to: -------> " + admin);

            //transaction.commit();
            return admin;
        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Admin getAdminByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query<Admin> query = session.createNamedQuery("Admin_findByUsername", Admin.class);
            query.setParameter("username", username);
            Admin result = query.getSingleResult();
            System.out.println("Object with this username is: ----> " + result);

            return result;
        }
    }

    @Override
    public boolean insertAdmin(Admin admin) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the admin object
            session.save(admin);
            // commit transaction
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAdmin(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            Query query = session.createQuery("delete from Admin where id =: id");
            query.setParameter("id", id);

            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}