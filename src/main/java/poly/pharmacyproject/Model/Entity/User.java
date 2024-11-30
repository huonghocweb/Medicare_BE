package poly.pharmacyproject.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name="user_name")
    private String userName;

    @Column(name="full_name")
    private String fullName;

    @Column(name="password")
    private String password;

    @Column(name="gender")
    private Boolean gender;

    @Column(name="address")
    private String address;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="birthday")
    private LocalDate birthday;

    @Column(name="email")
    private String email;

    @Column(name="status")
    private Boolean status;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private List<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<WishList> wishLists;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<DeliveryAddress> deliveryAddresses;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Cart> carts;
}
