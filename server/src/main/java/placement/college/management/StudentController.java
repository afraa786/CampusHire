package placement.college.management;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students") // Base URL for student endpoints
public class StudentController {

    @Autowired
    private Studentservice studentService;

    // GET: /students - Get all students
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    } 

    // GET: /students/{id} - Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: /students - Create a new student
    @PostMapping("create-student")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null); // You can add error details here
        }
        Student savedStudent = studentService.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    // PUT: /students/{id} - Update an existing student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student student, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null); // You can add error details here
        }

        Optional<Student> existingStudent = studentService.findById(id);
        if (!existingStudent.isPresent()) {
            return ResponseEntity.notFound().build(); // Return 404 if student is not found
        }

        student.setId(id); // Ensure the ID is set for updating
        Student updatedStudent = studentService.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    // DELETE: /students/{id} - Delete a student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable Long id) {
        boolean isDeleted = studentService.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // 204 No Content for successful deletion
        }
        return ResponseEntity.notFound().build(); // 404 Not Found if student does not exist
    }

    // Student registration
    @PostMapping("/register")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.registerStudent(student), HttpStatus.CREATED);
    }

    // Student login
    @PostMapping("/login")
    public ResponseEntity<String> loginStudent(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        Optional<Student> student = studentService.login(email, password);
        return student.map(value -> ResponseEntity.ok("Login successful!"))
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
    }

    // Student deletes their own account
    @DeleteMapping("Self-deletion/{id}")
    public ResponseEntity<String> deleteOwnAccount(@PathVariable Long id) {
        studentService.deleteOwnAccount(id);
        return ResponseEntity.ok("Your account has been deleted.");
    }

    // Admin deletes any student account
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteStudentByAdmin(@PathVariable Long id) {
        studentService.deleteStudentByAdmin(id);
        return ResponseEntity.ok("Student deleted by admin.");
    }
}