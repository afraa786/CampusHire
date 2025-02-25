package placement.college.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private Studentrepository studentRepository;
    
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(AuthRequest request) {
        String role = request.getRole().toString();

        if ("STUDENT".equalsIgnoreCase(role)) {
            Optional<Student> student = studentRepository.findByEmail(request.getEmail());
            if (student.isPresent() && passwordEncoder.matches(request.getPassword(), student.get().getPassword())) {
                String token = jwtUtil.generateToken(request.getEmail());
                return new AuthResponse(token, "STUDENT");
            }
        } else if ("ADMIN".equalsIgnoreCase(role)) {
            Optional<Admin> admin = adminRepository.findByEmail(request.getEmail());
            if (admin.isPresent() && passwordEncoder.matches(request.getPassword(), admin.get().getPassword())) {
                String token = jwtUtil.generateToken(request.getEmail());
                return new AuthResponse(token, "ADMIN");
            }
        } else if ("COMPANY".equalsIgnoreCase(role)) {
            Optional<Company> company = companyRepository.findByEmail(request.getEmail());
            if (company.isPresent() && passwordEncoder.matches(request.getPassword(), company.get().getPassword())) {
                String token = jwtUtil.generateToken(request.getEmail());
                return new AuthResponse(token, "COMPANY");
            }
        }

        throw new RuntimeException("Invalid email, password, or role");
    }
}
