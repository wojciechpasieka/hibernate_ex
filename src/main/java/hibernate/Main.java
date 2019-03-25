package hibernate;

import hibernate.dao.AdminDao;
import hibernate.dao.StatesDao;
import hibernate.dao.ToursDao;
import hibernate.dao.impl.AdminDaoImpl;
import hibernate.dao.impl.StatesDaoImpl;
import hibernate.dao.impl.ToursDaoImpl;
import hibernate.model.Admin;
import hibernate.model.Package;
import hibernate.model.Tour;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        HibernateConfig config = new HibernateJavaConfig();
        SessionFactory sessionFactory = config.getSessionFactory();

        Package myPackage = new Package();
        myPackage.setPackagedescription("My Desc");
        myPackage.setPackagegraphic("graphic XDD");
        myPackage.setPackagetitle("My title");

        Tour tour = new Tour();
        tour.setName("tour 1");
        tour.setBlurb("Blurb 1");
        tour.setPackageId(myPackage);

        ToursDao tourDao = new ToursDaoImpl(sessionFactory);

        tourDao.insertTour(tour);
        tourDao.getToursByPackageId(1);

        StatesDao statesDao = new StatesDaoImpl(sessionFactory.createEntityManager());
        System.out.println(statesDao.findAll());



        System.out.println("\n\n State by id with criteria: ");
        System.out.println(statesDao.getStateById("TX"));
        System.out.println("\n\n");


        Admin admin = new Admin();
        admin.setUsername("hibernate_admin");
        admin.setPassword("pass");


        AdminDao adminDao = new AdminDaoImpl(sessionFactory);
        System.out.println("Admin by id: " + adminDao.getAdminById(1));
        //System.out.println("Delete admin: " + adminDao.deleteAdmin(3));
        System.out.println("Admin by name: " + adminDao.getAdminByUsername("hibernate_admin"));

        try (Session session = sessionFactory.openSession()) {
            List<Admin> adminList = session.createQuery("from Admin", Admin.class).list();
            adminList.forEach(System.out::println);
        }

        config.shutdown();
    }
}
