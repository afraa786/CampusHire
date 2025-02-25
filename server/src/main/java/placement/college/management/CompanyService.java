package placement.college.management;

import java.util.List;

public interface CompanyService {
    Company createCompany(Company company);
    List<Company> getAllCompanies();
    Company getCompanyById(Long id);
    void deleteCompany(Long id);
    Company updateCompany(Long id, Company company);
    List<Student> getEligibleStudents(Long companyId);
}
