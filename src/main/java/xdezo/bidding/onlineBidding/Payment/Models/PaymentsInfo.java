package xdezo.bidding.onlineBidding.Payment.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentsInfo {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String productCode;
    @Column(nullable = false)
    private long amount;
    private float deliveryCharge;
    private float serviceCharge;

    private long taxAmount;
    @Column(nullable = false)
    private Double totalAmount;

}