package placement.college.management;

    import java.util.List;

    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
    
//CCONTROLLER M BANTI HAI APIsay
    @RestController
    @RequestMapping("/api/companies")
    public class Companycontroller {
    
        private final CompanyService companyService;
    
        public Companycontroller(CompanyService companyService) {
            this.companyService = companyService;
        }
    
        // Create a new company
        @PostMapping
        public ResponseEntity<Company> createCompany(@RequestBody Company company) {
            return new ResponseEntity<>(companyService.createCompany(company), HttpStatus.CREATED);
        }
    
        // Get all companies
        @GetMapping
        public ResponseEntity<List<Company>> getAllCompanies() {
            return ResponseEntity.ok(companyService.getAllCompanies());
        }
    
        // Get company by ID
        @GetMapping("/{id}")
        public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
            return ResponseEntity.ok(companyService.getCompanyById(id));
        }
    
        // Update company
    
    
        // Delete company
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
            companyService.deleteCompany(id);
            return ResponseEntity.ok("Company deleted successfully");
        }
    }