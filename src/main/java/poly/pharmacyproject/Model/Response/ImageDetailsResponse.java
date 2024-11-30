package poly.pharmacyproject.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDetailsResponse {
    private Integer imageDetailsId;
    private String imageUrl;
}
