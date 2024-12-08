package poly.pharmacyproject.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_status_actions")
public class OrderStatusAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_status_action_id")
    private Integer orderStatusActionId;

    @Column(name="order_status_action_name")
    private String orderStatusActionName;

    @Column(name="order_status_action_endpoint")
    private String orderStatusActionEndpoint;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="order_status_id")
    private OrderStatus orderStatus;


}
