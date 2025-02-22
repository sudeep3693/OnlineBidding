package xdezo.bidding.onlineBidding.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import xdezo.bidding.onlineBidding.Enums.UserRoles;
import xdezo.bidding.onlineBidding.Model.Bids;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@Table(name = "usersbidders", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @NotBlank
    @Column(nullable = false)
    private String phoneNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoles role;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss.SSS")
    private LocalDateTime createdAt;

    @NotNull
    @Column(length = 100)
    private String imageUrl;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Payments payments;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "users_address")
    private Address address;
}
