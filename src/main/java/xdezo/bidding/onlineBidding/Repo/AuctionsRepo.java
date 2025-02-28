package xdezo.bidding.onlineBidding.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xdezo.bidding.onlineBidding.Model.Auctions;

import java.util.List;
import java.util.Optional;

public interface AuctionsRepo extends JpaRepository<Auctions,Long> {





    List<Auctions> findByStatus(String status);

  Auctions findByTitle(String title);
}
