package placement.college.management;

import java.util.Optional;
import java.util.List;

public interface Studentservice {

    // Create and register student
    Student createStudent(Student student);
    
    Student registerStudent(Student student);

    // Student login method
    Optional<Student> login(String email, String password);

    // Delete student account
    void deleteOwnAccount(Long studentId);
    
    // Admin can delete student account
    void deleteStudentByAdmin(Long id);
    
    // Get all students
    List<Student> getAllStudents(); 

    // Get eligible companies for a student
    List<Company> getEligibleCompanies(Long studentId); 

    // Find a student by ID
    Optional<Student> findById(Long id);

    //Save a student
    Student save(Student student);

    // Delete a student by ID
    boolean deleteById(Long id);
    
    // Update a student by ID
    Student updateById(Long id, Student student);

    // Get students by specific criteria
    List<Student> getStudentsByCriteria(Integer minYear, Double minCgpa, Integer maxBacklogs);
    
    // Get companies by specific criteria
    List<Company> getCompaniesByCriteria(Integer minYear, Double minCgpa, Integer maxBacklogs);

    // Get all students who have applied to a company
    List<Student> getStudentsByCompany(Long companyId);
}
