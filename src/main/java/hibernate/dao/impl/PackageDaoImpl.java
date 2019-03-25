package hibernate.dao.impl;

import hibernate.dao.PackageDao;
import hibernate.model.Package;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PackageDaoImpl implements PackageDao {

    private final SessionFactory sessionFactory;

    public PackageDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Package> findAll() {
        try (Session session = sessionFactory.openSession()) {
            List<Package> packageList = session.createQuery("from Package", Package.class).list();
            packageList.forEach(e -> System.out.println(e));

            return packageList;
        }
    }

    @Override
    public Package getPackageById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Package aPackage = session.load(Package.class, id);
            System.out.println(aPackage);

            return aPackage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Package> getPackageByPackageTitle(String packageName) {
        try (Session session = sessionFactory.openSession()) {

            Query<Package> query = session.createQuery("from Package where packagetitle =: name", Package.class);
            query.setParameter("name", packageName);

            List<Package> resultList = query.getResultList();
            System.out.println("Package with this id is: -----> " + resultList);
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insertPackage(Package box) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.save(box);
            transaction.commit();

            System.out.println("Dodano podany obiekt :)");
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
    public boolean deletePackage(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery("delete from Package where id =: id");
            query.setParameter("id", id);

            query.executeUpdate();
            transaction.commit();

            System.out.println("Usunięto obiekt o podanym id :(");
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
