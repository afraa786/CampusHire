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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "placement_criteria") // Optional: Specify table name
public class Placementcriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    @NotBlank(message = "Criteria is required")
    private String criteria;

    @NotNull(message = "CTC is required")
    private BigDecimal ctc;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotBlank(message = "Degree is required")
    private String degree;

    @NotBlank(message = "Year is required")
    private String year;

    private String branch;

    @DecimalMin(value = "0.0", message = "CGPA must be positive")
    private Double cgpa;

    @DecimalMin(value = "0.0", message = "Tenth percentage must be positive")
    private Double tenth;

    @DecimalMin(value = "0.0", message = "Twelfth percentage must be positive")
    private Double twelfth;

    private Double diploma;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer backlog = 0;

    private Integer gap = 0;

    private Integer activeBacklog = 0;

    // Many-to-Many Relationship
    @OneToOne(mappedBy = "placementCriteria") 
    private List<Company> companies;

    public void setId(Long id) {
        if (this.id != null) {
            throw new UnsupportedOperationException("ID is auto-generated and cannot be changed.");
        }
    }
}
