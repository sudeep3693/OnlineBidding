package xdezo.bidding.onlineBidding.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import xdezo.bidding.onlineBidding.EntitiesCategories.AuctionCategory;
import xdezo.bidding.onlineBidding.Enums.AuctionStatus;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auctions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    @NotNull
    @Column(nullable = false)
    private Double starting_price;

    @NotNull
    @Column(nullable = false)
    private Double current_price;

    @NotNull
    @Column(nullable = false)
    private Double reserve_price;

    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime start_time;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime end_time;

    @Column(updatable = false, nullable = false)
    private Long seller_id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuctionStatus status;

    @CreationTimestamp
    @Column(updatable = false)
    private Date created_at;

    @Column(nullable = false, length = 500)
    private String image_url;

    @Column(nullable = false)
    private Double buy_now_price;

    @Column(nullable = false)
    private Double bid_increment;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "auction", orphanRemoval = true)
    private Payments payment_id;



    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private AuctionCategory category;
}
