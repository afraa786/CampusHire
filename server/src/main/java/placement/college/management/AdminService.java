package placement.college.management;

import java.util.Optional;
import java.util.List;

public interface AdminService {

    Admin createAdmin(Admin admin); // Register a new admin

    Optional<Admin> login(String email, String password); // Admin login

    void deleteAdmin(Long id); // Delete an admin

    List<Admin> getAllAdmins(); // Get all admins

    Optional<Admin> findById(Long id); // Find admin by ID
}
