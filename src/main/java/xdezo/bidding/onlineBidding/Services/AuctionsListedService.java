package xdezo.bidding.onlineBidding.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xdezo.bidding.onlineBidding.EntitiesCategories.AuctionCategory;
import xdezo.bidding.onlineBidding.Model.Auctions;
import xdezo.bidding.onlineBidding.Model.AuctionImages;
import xdezo.bidding.onlineBidding.Repo.AuctionsRepo;
import xdezo.bidding.onlineBidding.Repo.CategoryRepo;

import java.util.List;

@Service
public class AuctionsListedService {

    private final AuctionsRepo itemsRepo;
    private final CategoryRepo categoryRepo;

    public AuctionsListedService(AuctionsRepo itemsRepo, CategoryRepo categoryRepo) {
        this.itemsRepo = itemsRepo;
        this.categoryRepo = categoryRepo;
    }

    // Read-only transaction for fetching data
    @Transactional(readOnly = true)
    public List<Auctions> getAllItems() {
        return itemsRepo.findAll();
    }

    // Transactional method for adding an auction
    @Transactional(rollbackFor = Exception.class)
    public Auctions addAuction(Auctions auction) {
        // Handle category association
        String categoryTitle = auction.getCategory_title().toUpperCase();
        AuctionCategory category = categoryRepo.findByCategoryTitle(categoryTitle);

        if (category == null) {
            throw new IllegalArgumentException("Category not found!");
        }

        auction.setCategory(category);

        // Handle images association
        if (auction.getImage() != null && !auction.getImage().isEmpty()) {
            for (AuctionImages image : auction.getImage()) {
                image.setAuction(auction);
            }
        }

        return itemsRepo.save(auction);
    }
}
