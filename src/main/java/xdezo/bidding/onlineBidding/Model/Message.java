package xdezo.bidding.onlineBidding.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @OneToOne
    @JoinColumn(name="receiver_id")
    private User receiver;


    @OneToOne
    @JoinColumn(name = "sender_id")
    private User sender;


    @NotBlank
    @Column(length = 300)
    private String message;

    @CurrentTimestamp
    @NotBlank
    private String sent_at;
}
