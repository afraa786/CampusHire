package placement.college.management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Studentrepository
 extends JpaRepository<Student, Long>  {

    // JpaRepository provides CRUD methods by default (save, findAll, findById, deleteById)
}