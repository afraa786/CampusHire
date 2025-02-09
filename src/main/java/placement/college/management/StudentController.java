package placement.college.management;

import java.util.List;
import java.util.Optional;

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
    @GetMapping
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
    @PostMapping
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
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            studentService.deleteById(id);
            return ResponseEntity.noContent().build(); // Successful deletion
        }
        return ResponseEntity.notFound().build(); // Return 404 if student not found
    }
}
