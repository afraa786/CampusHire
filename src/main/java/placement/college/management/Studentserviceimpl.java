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
    public List<Student> findAll() {
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
            return studentRepository.save(student);
        }
        return null; // Or throw an exception
    }

    @Override
    public boolean deleteById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


