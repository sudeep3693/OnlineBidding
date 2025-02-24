package xdezo.bidding.onlineBidding.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xdezo.bidding.onlineBidding.Model.Auctions;

import java.util.List;

public interface AuctionsRepo extends JpaRepository<Auctions,Long> {

    @Override

    List<Auctions>  findAll();

    List<Auctions> findByStatus(String status);
}
