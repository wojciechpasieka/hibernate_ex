package hibernate.dao;

import hibernate.model.Package;

import java.util.List;

public interface PackageDao {

    List<Package> findAll();

    Package getPackageById(int id);

    List<Package> getPackageByPackageTitle(String packageName);

    boolean insertPackage(Package box);

    boolean deletePackage(int id);

}
