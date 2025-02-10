package placement.college.management;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

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

    @DecimalMin(value = "0.0", message = "Minimum CTC must be positive", inclusive = false)
    private BigDecimal minCtc;

    @DecimalMin(value = "0.0", message = "Maximum CTC must be positive", inclusive = false)
    private BigDecimal maxCtc;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "placement_criteria_id", nullable = false)
    private Placementcriteria placementCriteria;
}
