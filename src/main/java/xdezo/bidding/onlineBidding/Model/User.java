package xdezo.bidding.onlineBidding.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import xdezo.bidding.onlineBidding.Enums.UserRoles;
import xdezo.bidding.onlineBidding.Model.Bids;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity

@Builder
@Table(name = "usersbidders", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@ToString(exclude = "address")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email(message = "Invalid email format")
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
    private LocalDateTime createdAt;

    @NotNull
    @Column(length = 100)
    private String imageUrl = "default image";


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "payer_id")
    private Payments payer;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "payee_id")
    private Payments payee;



    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user_id")
    @Column(nullable = false)
    private List<Notifications> notification;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "bidder_id")
    @Column(nullable = false)
    private List<Bids> bids;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "users_address")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "sender")
    private Message sender;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "receiver")
    private Message receiver;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "sellerId")
    private Review seller;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "reviewerId")
    private Review reviewer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seller_id")
    private List<Auctions> seller_Id;
}
