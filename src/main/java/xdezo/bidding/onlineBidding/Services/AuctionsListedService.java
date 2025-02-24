package xdezo.bidding.onlineBidding.Services;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xdezo.bidding.onlineBidding.Model.Auctions;
import xdezo.bidding.onlineBidding.Repo.AuctionsRepo;

import java.util.Collections;
import java.util.List;

@Service
public class AuctionsListedService {

    private final AuctionsRepo itemsRepo;

    public AuctionsListedService(AuctionsRepo itemsRepo) {
        this.itemsRepo = itemsRepo;
    }

    // Read-only transaction for fetching data
    @Transactional(readOnly = true)
    public List<Auctions> getAllItems() {
        return itemsRepo.findAll();
    }

    // Transactional method for adding an auction
    @Transactional(rollbackFor = Exception.class)
    public List<Auctions> addAuction(Auctions auctions) {
        try {
            // Save the auction and return it in a singleton list
            validateCategory(auctions);
            Auctions savedAuction = itemsRepo.save(auctions);
            return Collections.singletonList(savedAuction);
        } catch (Exception e) {
            // Log the exception and rethrow it to trigger rollback
            throw new ServiceException("Failed to add auction", e);
        }
    }
}
