package xdezo.bidding.onlineBidding.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xdezo.bidding.onlineBidding.Model.Auctions;

public interface AuctionsRepo extends JpaRepository<Auctions,Long> {



}
