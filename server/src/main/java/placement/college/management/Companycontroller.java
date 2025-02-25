package placement.college.management;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/companies")
public class Companycontroller {

    private final CompanyService companyService;

    public Companycontroller(CompanyService companyService) {
        this.companyService = companyService;
    }

    // Create a new company (Requires authentication)
    @PostMapping("/create-company")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPANY')")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        return new ResponseEntity<>(companyService.createCompany(company), HttpStatus.CREATED);
    }

    // Get all companies (Public access)
    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    // Get company by ID (Public access)
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    // Update company (Requires authentication)
    // Only Admins and the company itself can update the company
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPANY')")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        return ResponseEntity.ok(companyService.updateCompany(id, company));
    }

    @GetMapping("/{id}/eligible-students")
    public ResponseEntity<List<Student>> getEligibleStudents(@PathVariable Long id) {
        List<Student> students = companyService.getEligibleStudents(id);
        return ResponseEntity.ok(students);
    }

    // Delete company (Only Admins can delete)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok("Company deleted successfully");
    }
}