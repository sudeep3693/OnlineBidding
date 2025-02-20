package xdezo.bidding.onlineBidding.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import xdezo.bidding.onlineBidding.Enums.ItemsStatus;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    private Long id;


    @Column(nullable = false , unique = true, length = 50)
    private String title;

    @Column(nullable = false , length = 500)
    private String description;


@Column(nullable = false, length = 50 )
    private Double startingPrice;

    @Column(nullable = false, length = 50 )
    @NotBlank
    private Double currentPrice;


    @Column(updatable = false)
    private LocalDateTime start_time;


    @NotBlank
    @Column
    private LocalDateTime end_time;


    @Column(updatable = false, nullable = false)
    private Long seller_id;


    @NotBlank
    @Enumerated(EnumType.STRING)
    private ItemsStatus status;


    @CreationTimestamp
    @Column(updatable = false)
    private Date created_at;



}
