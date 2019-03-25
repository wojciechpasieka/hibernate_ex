package hibernate.dao;

import hibernate.model.Tour;

import java.util.List;

public interface ToursDao {

    List<Tour> findAll();

    Tour getTourById(int id);

    boolean deleteTour(int id);

    List<Tour> getToursByPackageId(int packageEntity);

    Tour insertTour(Tour tour);
}

