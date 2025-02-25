package placement.college.management;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "placement_criteria")
public class Placementcriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Criteria is required")
    private String criteria;

    @NotNull(message = "CTC is required")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal ctc;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotBlank(message = "Degree is required")
    @Column(nullable = false)
    private String degree;

    @NotNull(message = "Year is required")
    @Column(nullable = false)
    private Integer year;

    @NotBlank(message = "Branch is required")
    @Column(nullable = false)
    private String branch;

    @Min(value = 1, message = "Minimum year should be at least 1")
    private int minYear;

    @DecimalMin(value = "0.0", message = "CGPA must be positive", inclusive = false)
    @DecimalMax(value = "10.0", message = "CGPA cannot exceed 10.0")
    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal cgpa;

    @DecimalMin(value = "0.0", message = "Minimum CGPA must be positive", inclusive = false)
    @DecimalMax(value = "10.0", message = "Minimum CGPA cannot exceed 10.0")
    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal minCgpa;

    @NotNull(message = "Max backlogs are required")
    @Min(value = 0, message = "Backlogs cannot be negative")
    private Integer maxBacklogs;

    @DecimalMin(value = "0.0", message = "Tenth percentage must be positive", inclusive = false)
    @DecimalMax(value = "100.0", message = "Tenth percentage cannot exceed 100.0")
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal tenth;

    @DecimalMin(value = "0.0", message = "Twelfth percentage must be positive", inclusive = false)
    @DecimalMax(value = "100.0", message = "Twelfth percentage cannot exceed 100.0")
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal twelfth;

    @DecimalMin(value = "0.0", message = "Diploma percentage must be positive", inclusive = false)
    @DecimalMax(value = "100.0", message = "Diploma percentage cannot exceed 100.0")
    @Column(precision = 5, scale = 2)
    private BigDecimal diploma;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer backlog;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer gap;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer activeBacklog;

    @OneToMany(mappedBy = "placementCriteria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Company> companies;
}
