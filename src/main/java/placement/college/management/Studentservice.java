package placement.college.management;

import java.util.List;
import java.util.Optional;

public interface Studentservice {
    
    List<Student> findAll();
    
    Optional<Student> findById(Long id);
    
    Student save(Student student);
    
    Student updateById(Long id, Student student);
    
    boolean deleteById(Long id);
}