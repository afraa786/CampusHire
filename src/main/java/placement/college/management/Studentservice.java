package placement.college.management;

import java.lang.*;
import java.util.List;
import java.util.Optional;

public interface Studentservice {


    List<Student> getAllStudents(); 
    
    // Find a student by ID
    Optional<Student> findById(Long id);
    
    // Save a student (create or update)
    Student save(Student student);
    
    // Update a student by ID

    Student updateById(Long id, Student student);
    
    // Delete a student by ID
    boolean deleteById(Long id);

}