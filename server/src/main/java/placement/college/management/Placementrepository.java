package placement.college.management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Placementrepository extends JpaRepository<Placementcriteria, Long>  {

    // JpaRepository provides CRUD methods by default (save, findAll, findById, deleteById)
}