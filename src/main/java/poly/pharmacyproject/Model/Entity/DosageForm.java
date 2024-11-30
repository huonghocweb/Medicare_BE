package poly.pharmacyproject.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="dosage_form")
public class DosageForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dosage_form_id")
    private Integer dosageFormId;

    @Column(name="dosage_form_name")
    private String dosageFormName;

    @Column(name="instructions")
    private String instructions;

    @JsonIgnore
    @OneToMany(mappedBy = "dosageForm")
    private List<Variation> variations;

}
