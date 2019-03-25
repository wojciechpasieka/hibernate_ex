package hibernate.querysRun;

import hibernate.HibernateConfig;
import hibernate.HibernateJavaConfig;
import hibernate.dao.impl.ToursDaoImpl;
import hibernate.model.Tour;
import org.hibernate.SessionFactory;

public class TourDaoRun {
    public static void main(String[] args) {
        HibernateConfig hibernateConfig = new HibernateJavaConfig();
        SessionFactory sessionFactory = hibernateConfig.getSessionFactory();

        ToursDaoImpl toursDao = new ToursDaoImpl(sessionFactory);

        //findAll
        toursDao.findAll();

        //deleteById
        toursDao.deleteTour(28);

        //getById
        toursDao.getTourById(26);

        //insertInto
        Tour tour = new Tour();
        tour.setName("How to be a gentleman");
        tour.setBlurb("Some blurb");

        toursDao.insertTour(tour);


        //getToursByPackageId
        toursDao.getToursByPackageId(9);


        hibernateConfig.shutdown();
    }
}
