package poly.pharmacyproject.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackagingResponse {
    private Integer packageId;
    private String packageType;
    private Integer quantity;
    private String unit;
}
