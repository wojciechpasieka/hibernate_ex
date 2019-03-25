package hibernate.querysRun;

import hibernate.HibernateConfig;
import hibernate.HibernateJavaConfig;
import hibernate.dao.impl.PackageDaoImpl;
import hibernate.model.Package;
import org.hibernate.SessionFactory;

public class PackageDaoRun {
    public static void main(String[] args) {

        HibernateConfig config = new HibernateJavaConfig();
        SessionFactory sessionFactory = config.getSessionFactory();

        PackageDaoImpl packageDao = new PackageDaoImpl(sessionFactory);

        //findAll();
        packageDao.findAll();

        //getPackageById(int id);
        packageDao.getPackageById(3);

        //getPackageByPackageTitle(String packageName);
        packageDao.getPackageByPackageTitle("California Hotsprings");

        //insertPackage(Package box);
        Package aPackage = new Package();
        aPackage.setPackagetitle("ExampleTitle");
        aPackage.setPackagegraphic("exampleGraphic");
        aPackage.setPackagedescription("exampleDescrription");

        packageDao.insertPackage(aPackage);


        //deletePackage(int id)
        packageDao.deletePackage(11);



        config.shutdown();
    }
}
