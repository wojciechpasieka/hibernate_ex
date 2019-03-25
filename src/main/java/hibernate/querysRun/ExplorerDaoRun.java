package hibernate.querysRun;

import hibernate.HibernateConfig;
import hibernate.HibernateJavaConfig;
import hibernate.dao.impl.ExplorerDaoImpl;
import hibernate.model.Explorer;
import org.hibernate.SessionFactory;

public class ExplorerDaoRun {
    public static void main(String[] args) {
        HibernateConfig config = new HibernateJavaConfig();
        SessionFactory sessionFactory = config.getSessionFactory();

        ExplorerDaoImpl explorerDao = new ExplorerDaoImpl(sessionFactory);

        //getById
        explorerDao.getExplorerById(2);


        //findALL
        explorerDao.findAll();


        //insertExplorer
        Explorer explorer = new Explorer();
        explorer.setFirstName("Wojciech");
        explorer.setLastName("Pasieka");

        explorerDao.insertExplorer(explorer);


        //deleteExplorerByID
        explorerDao.deleteExplorer(4);

        //getExplorersByStateId
        explorerDao.getExplorersByStateId("CA");



        config.shutdown();
    }
}
