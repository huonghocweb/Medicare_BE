package poly.pharmacyproject.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="attribute")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="attribute_id")
    private Integer attributeId;

    @Column(name="attribute_name")
    private Integer attributeName;

    @Column(name="description")
    private Integer description;
}
