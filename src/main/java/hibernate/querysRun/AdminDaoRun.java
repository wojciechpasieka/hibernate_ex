package hibernate.querysRun;

import hibernate.HibernateConfig;
import hibernate.HibernateJavaConfig;
import hibernate.dao.impl.AdminDaoImpl;
import hibernate.model.Admin;
import org.hibernate.SessionFactory;

public class AdminDaoRun {
    public static void main(String[] args) {
        HibernateConfig config = new HibernateJavaConfig();
        SessionFactory sessionFactory = config.getSessionFactory();


        AdminDaoImpl adminDao = new AdminDaoImpl(sessionFactory);

        //find all elements
        adminDao.findAll();

        //getAdminById(int id);
        adminDao.getAdminById(2);


        //getAdminByUsername(String username)
        adminDao.getAdminByUsername("explorerone");

        //insertAdmin(Admin admin);
        Admin admin = new Admin();
        admin.setUsername("Wojciech1993");
        admin.setPassword("newPassword");

        adminDao.insertAdmin(admin);


        //deleteAdmin(int id);
        adminDao.deleteAdmin(5);


        config.shutdown();
    }
}
