package placement.college.management;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Studentserviceimpl implements Studentservice {
    
    @Autowired
    private Studentrepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateById(Long id, Student student) {
        if (studentRepository.existsById(id)) {
            student.setId(id); // Ensure ID consistency
            return studentRepository.save(student); // Save updated student
        }
        throw new RuntimeException("Student not found with ID: " + id); // Exception handling
    }

    @Override
    public boolean deleteById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;  // Indicate successful deletion
        }
        return false;  // Indicate failure if student does not exist
    }
    

    }