package xdezo.bidding.onlineBidding.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notifications {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    private String Message;

    private Boolean is_read;

    private String created_at;



}
