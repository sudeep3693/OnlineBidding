package xdezo.bidding.onlineBidding.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xdezo.bidding.onlineBidding.Enums.PaymentMethod;
import xdezo.bidding.onlineBidding.Enums.PaymentStatus;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull // Changed from @NotBlank (only for Strings)
    @Column(nullable = false)
    private Double amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentMethod payment_method;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentStatus payment_status;

    @NotBlank
    @Column(nullable = false, updatable = false, unique = true)
    private String transaction_id;

    @NotNull // Changed from @NotBlank
    @Column(nullable = false)
    private LocalDateTime payment_date;

    @OneToOne
    @JoinColumn(name = "auction_id")
    private Auctions auction; // Changed from auction_id

    @OneToOne
    @JoinColumn(name = "user_id")// Must match the field in User.java
    private User user;
}
