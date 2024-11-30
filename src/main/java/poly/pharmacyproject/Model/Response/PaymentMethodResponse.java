package poly.pharmacyproject.Model.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodResponse {
    private Integer paymentMethodId;
    private String paymentName;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String imageUrl;
}
