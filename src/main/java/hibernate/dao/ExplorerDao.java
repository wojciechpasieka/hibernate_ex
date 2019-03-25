package hibernate.dao;

import hibernate.model.Explorer;

import java.util.List;

public interface ExplorerDao {

    List<Explorer> findAll();

    Explorer getExplorerById(int id);

    boolean insertExplorer(Explorer explorer);

    boolean deleteExplorer(int id);

    List<Explorer> getExplorersByStateId(String stateId);

}
