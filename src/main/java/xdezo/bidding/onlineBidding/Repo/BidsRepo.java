package xdezo.bidding.onlineBidding.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xdezo.bidding.onlineBidding.Model.Bids;

public interface BidsRepo extends JpaRepository<Bids, Long> {

}
