package xdezo.bidding.onlineBidding.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bids {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Items item;

    @ManyToOne
    @JoinColumn(name = "bidder_id", nullable = false)
    private User bidder;

    @Column(nullable = false)
    private double bidAmount;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime bidTime; // Auto-generate timestamp
}
