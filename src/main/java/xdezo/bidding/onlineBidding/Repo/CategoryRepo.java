package xdezo.bidding.onlineBidding.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xdezo.bidding.onlineBidding.EntitiesCategories.AuctionCategory;

public interface CategoryRepo extends JpaRepository<AuctionCategory,Long> {

}
