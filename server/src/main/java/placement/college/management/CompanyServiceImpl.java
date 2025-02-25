package placement.college.management;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.math.BigDecimal;
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private Studentrepository studentRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
    }

    @Override
    public void deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException("Company not found with id: " + id);
        }
        companyRepository.deleteById(id);
    }

    @Override
    public Company updateCompany(Long id, Company company) {
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException("Company not found with id: " + id);
        }
        company.setId(id);
        return companyRepository.save(company);
    }

    @Override
public List<Student> getEligibleStudents(Long companyId) {
    Company company = companyRepository.findById(companyId).orElse(null);
    if (company == null) {
        return List.of(); // If company not found, return empty list
    }

    Placementcriteria criteria = company.getPlacementCriteria();
    
    return studentRepository.findAll().stream()
            .filter(student -> student.getYear() >= criteria.getMinYear() &&
                               student.getCgpa() >= company.getMinCgpa().doubleValue() &&
                               student.getCgpa() <= company.getMaxCgpa().doubleValue() &&
                               student.getBacklogs() <= criteria.getMaxBacklogs())
            .collect(Collectors.toList());
}

    
}
// @Override
// public List<Company> getEligibleCompanies(Long studentId) {
//     Student student = studentRepository.findById(studentId).orElse(null);
//     if (student == null) {
//         return List.of(); // If student not found, return empty list
//     }

//     return companyRepository.findAll().stream()
//             .filter(company -> {
//                 Placementcriteria criteria = company.getPlacementCriteria();
//                 return student.getYear() >= criteria.getMinYear() && 
//                        student.getCgpa() >= criteria.getMinCgpa() &&
//                        student.getBacklogs() <= criteria.getMaxBacklogs();
//             })
//             .collect(Collectors.toList());