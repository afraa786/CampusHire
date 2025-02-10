package placement.college.management;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Getter
@Setter
@Table(name = "placement_criteria")
public class Placementcriteria {  // Fixed class name
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @DecimalMin(value = "0.0", message = "CGPA must be positive", inclusive = false)
    private Double cgpa;

    @DecimalMin(value = "0.0", message = "Tenth percentage must be positive", inclusive = false)
    private Double tenth;

    @DecimalMin(value = "0.0", message = "Twelfth percentage must be positive", inclusive = false)
    private Double twelfth;

    private Double diploma;

    private Integer backlog = 0;
    private Integer gap = 0;
    private Integer activeBacklog = 0;

    @OneToMany(mappedBy = "placementCriteria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Company> companies;

}
