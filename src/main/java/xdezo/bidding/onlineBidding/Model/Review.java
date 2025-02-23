package xdezo.bidding.onlineBidding.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(updatable = false)
    private Long reviewer_id;
    @NotNull
    @Column(updatable = false)
    private Long seller_id;

    @NotNull
    @Min(1)
    @Max(5)
    private int rating;
    @NotBlank
    private String  comment;

    @NotBlank
    @CreationTimestamp
    private String created_at;


}
