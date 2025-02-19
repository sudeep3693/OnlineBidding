package xdezo.bidding.onlineBidding.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String City;

    @NotBlank
    @Column(nullable = false)
    private String Country;

    @NotBlank
    @Column(nullable = false)
    private String District;

    @NotBlank
    @Column(nullable = false)
    private String Province;

    @NotBlank
    @Column(nullable = false)
    private String Street;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true) // Foreign key column
    private User user;
}
