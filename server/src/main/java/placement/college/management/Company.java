package placement.college.management;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

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

    private String minCtc;
    private String maxCtc;
    private String phone;

    // Many-to-Many Relationship
    @OneToOne
    @JoinColumn(name = "placementCriteria", unique = true) // Ensures each company gets only one placement criteria
    private Placementcriteria placementCriteria;

}
