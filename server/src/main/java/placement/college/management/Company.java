package placement.college.management;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Company name is required")
    @Column(nullable = false, unique = true)
    private String name;

    @JsonIgnore // Hide password in JSON responses
    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Industry is required")
    @Column(nullable = false)
    private String industry;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Company contact email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Job role is required")
    @Column(nullable = false)
    private String jobRole;

    @NotNull(message = "Number of openings is required")
    @Min(value = 1, message = "Openings must be at least 1")
    private Integer openings;

    @Column(nullable = false, precision = 10, scale = 2)
    @DecimalMin(value = "0.1", message = "Minimum CTC must be positive", inclusive = true)
    private BigDecimal minCtc;

    @Column(nullable = false, precision = 10, scale = 2)
    @DecimalMin(value = "0.1", message = "Maximum CTC must be positive", inclusive = true)
    private BigDecimal maxCtc;

    @Column(nullable = false, precision = 3, scale = 2)
    @DecimalMin(value = "0.0", message = "Minimum CGPA must be positive", inclusive = false)
    @DecimalMax(value = "10.0", message = "CGPA cannot exceed 10.0")
    private BigDecimal minCgpa;

    @Column(nullable = false, precision = 3, scale = 2)
    @DecimalMin(value = "0.0", message = "Maximum CGPA must be positive", inclusive = false)
    @DecimalMax(value = "10.0", message = "CGPA cannot exceed 10.0")
    private BigDecimal maxCgpa;

    @Column(nullable = false, unique = true, length = 13)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "placement_criteria_id", nullable = false)
    private Placementcriteria placementCriteria;

    // Inverse side of the many-to-many relationship with Student
    @ManyToMany(mappedBy = "companies") // "companies" refers to the field in the Student entity
    private Set<Student> students;

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}
// package placement.college.management;

// import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.AllArgsConstructor;
// import jakarta.persistence.*;
// import jakarta.validation.constraints.*;
// import java.math.BigDecimal;

// @Data
// @Entity
// @NoArgsConstructor
// @AllArgsConstructor
// @Table(name = "companies")
// public class Company {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @NotBlank(message = "Company name is required")
//     @Column(nullable = false, unique = true)
//     private String name;

//     @NotBlank(message = "Industry is required")
//     @Column(nullable = false)
//     private String industry;

//     @Email(message = "Email should be valid")
//     @NotBlank(message = "Company contact email is required")
//     @Column(nullable = false, unique = true)
//     private String email;

//     @NotBlank(message = "Job role is required")
//     @Column(nullable = false)
//     private String jobRole;

//     @NotNull(message = "Number of openings is required")
//     @Min(value = 1, message = "Openings must be at least 1")
//     private Integer openings;

//     @DecimalMin(value = "0.0", message = "Minimum CTC must be positive", inclusive = false)
//     private BigDecimal minCtc;

//     @DecimalMin(value = "0.0", message = "Maximum CTC must be positive", inclusive = false)
//     private BigDecimal maxCtc;

//     private String phone;

//     @ManyToOne
//     @JoinColumn(name = "placement_criteria_id", nullable = false)
//     private Placementcriteria placementCriteria;
// }