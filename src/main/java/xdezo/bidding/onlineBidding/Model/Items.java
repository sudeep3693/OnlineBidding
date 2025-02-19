package xdezo.bidding.onlineBidding.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Double currentPrice;


    private Date start_time;


    private Date end_time;

    @Column(nullable = false)
    private Long seller_id;


    private String status;


    private Date created_at;



}
