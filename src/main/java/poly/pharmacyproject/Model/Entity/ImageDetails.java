package poly.pharmacyproject.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="image_deatails")
public class ImageDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_details_id")
    private Integer imageDetailsId;

    @Column(name="image_url")
    private String imageUrl;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="variation_id")
    private Variation variation;
}
