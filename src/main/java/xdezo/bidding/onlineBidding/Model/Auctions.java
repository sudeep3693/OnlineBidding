package xdezo.bidding.onlineBidding.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import xdezo.bidding.onlineBidding.EntitiesCategories.AuctionCategory;
import xdezo.bidding.onlineBidding.Enums.AuctionStatus;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Auctions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sellerId")
    private User seller_id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuctionStatus status;

    @Transient
    private String category_title;

    @CreationTimestamp
    @Column(updatable = false)
    private Date created_at;

    @Column(nullable = false, length = 500)
    private String image_url;

    @Column(nullable = false)
    private Double buy_now_price;

    @Column(nullable = false)
    private Double bid_increment;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "auction_id", orphanRemoval = true)
    private Payments payment_id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auctionId", orphanRemoval = true)
    private List<Bids> bid;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private AuctionCategory category;
}
