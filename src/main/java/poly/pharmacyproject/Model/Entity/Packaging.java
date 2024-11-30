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
@Table(name="packaging")
public class Packaging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="package_id")
    private Integer packageId;

    @Column(name="package_type")
    private String packageType;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="unit")
    private String unit;

    @JsonIgnore
    @OneToMany(mappedBy = "packaging")
    private List<Variation> variations;
}
