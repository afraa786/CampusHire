package placement.college.management;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "Department is required")
    private String department;

    @Column(nullable = false)
    @Min(value = 1, message = "Year must be at least 1")
    @Max(value = 4, message = "Year cannot be more than 4")
    private int year;

    @Column(nullable = false, unique = true)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    @NotBlank(message = "Phone number is required")
    private String phone;

    @Column(nullable = false)
    private int backlogs;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", message = "CGPA must be positive", inclusive = false)
    @NotNull(message = "CGPA is required")
    private Double cgpa;

    @Column(nullable = false)
    private String role = "STUDENT";

    @ManyToOne
    @JoinColumn(name = "placement_criteria_id", nullable = false)
    private Placementcriteria placementCriteria;

    // Define the many-to-many relationship with Company
    @ManyToMany
    @JoinTable(
        name = "student_companies", // Name of the join table
        joinColumns = @JoinColumn(name = "student_id"), // Foreign key for Student
        inverseJoinColumns = @JoinColumn(name = "company_id") // Foreign key for Company
    )
    private Set<Company> companies;
}