package placement.college.management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface Studentrepository
 extends JpaRepository<Student, Long>  {
    Optional<Student> findByEmail(String email); //✅ Added findByEmail → This helps in logging in students.
    // JpaRepository provides CRUD methods by default (save, findAll, findById, deleteById)
}