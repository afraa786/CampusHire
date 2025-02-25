package placement.college.management;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class Studentserviceimpl implements Studentservice {

    @Override
    public List<Student> getStudentsByCompany(Long companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        if (company == null) {
            return List.of();
        }
        return studentRepository.findAll().stream()
                .filter(student -> student.getCompanies().contains(company))
                .collect(Collectors.toList());
    }

    @Autowired
    private Studentrepository studentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private final PasswordEncoder passwordEncoder;


    public Studentserviceimpl(Studentrepository studentRepository, CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Student createStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword())); // Encrypt password
        return studentRepository.save(student);
    }

    public Student registerStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword())); // Encrypt password
        return studentRepository.save(student);
    }

    public Optional<Student> login(String email, String password) {
        Optional<Student> student = studentRepository.findByEmail(email);
        if (student.isPresent() && passwordEncoder.matches(password, student.get().getPassword())) {
            return student;
        }
        return Optional.empty();
    }

    @Override
    public void deleteOwnAccount(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public void deleteStudentByAdmin(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Company> getEligibleCompanies(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            return List.of();
        }

        return companyRepository.findAll().stream()
                .filter(company -> {
                    Placementcriteria criteria = company.getPlacementCriteria();
                    return student.getYear() >= criteria.getMinYear() &&
                           BigDecimal.valueOf(student.getCgpa()).compareTo(criteria.getMinCgpa()) >= 0 &&
                           student.getBacklogs() <= criteria.getMaxBacklogs();
                })
                .collect(Collectors.toList());
    }

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
            student.setId(id);
            return studentRepository.save(student);
        }
        throw new RuntimeException("Student not found with ID: " + id);
    }

    @Override
    public boolean deleteById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Student> getStudentsByCriteria(Integer minYear, Double minCgpa, Integer maxBacklogs) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getYear() >= minYear &&
                        student.getCgpa() >= minCgpa &&
                        student.getBacklogs() <= maxBacklogs)
                .collect(Collectors.toList());
    }

    @Override
    public List<Company> getCompaniesByCriteria(Integer minYear, Double minCgpa, Integer maxBacklogs) {
        return companyRepository.findAll().stream()
                .filter(company -> {
                    Placementcriteria criteria = company.getPlacementCriteria();
                    return criteria.getMinYear() >= minYear &&
                            criteria.getMinCgpa().compareTo(BigDecimal.valueOf(minCgpa)) >= 0 &&
                            criteria.getMaxBacklogs() <= maxBacklogs;
                })
                .collect(Collectors.toList());
    }
}

// ✅ Password Encryption → Prevents storing plain text passwords.
// ✅ Student Login → Validates credentials and returns the student.
// ✅ Student Deletes Own Account → Calls deleteById().
// ✅ Admin Deletes Any Student → Also calls deleteById().