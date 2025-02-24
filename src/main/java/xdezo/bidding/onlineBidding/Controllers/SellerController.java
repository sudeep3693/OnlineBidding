package xdezo.bidding.onlineBidding.Controllers;

import jdk.jfr.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xdezo.bidding.onlineBidding.EntitiesCategories.AuctionCategory;
import xdezo.bidding.onlineBidding.Model.Auctions;
import xdezo.bidding.onlineBidding.Services.AuctionsListedService;
import xdezo.bidding.onlineBidding.Services.CategoryService;

@RestController
@RequestMapping("/seller")
public class SellerController {

    private static final Logger logger = LoggerFactory.getLogger(SellerController.class);
    private final AuctionsListedService auctionsListedService;
    private final CategoryService categoryService;

    public SellerController(AuctionsListedService auctionsListedService, CategoryService categoryService) {
        this.auctionsListedService = auctionsListedService;
        this.categoryService = categoryService;
    }

    @GetMapping("/sellerHome")
    public String getSellerHome() {
        return "Home Seller";
    }

    @PostMapping("/addAuction")
    public ResponseEntity<?> addAuction(@RequestBody Auctions auction) {
        try {
            logger.info("Add auction request received: {}", auction);
            Auctions savedAuction = (Auctions) auctionsListedService.addAuction(auction);
            return ResponseEntity.ok(savedAuction);
        } catch (Exception e) {
            logger.error("Error adding auction: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error adding auction: " + e.getMessage());
        }
    }

    @PostMapping("/addCategory")
    public ResponseEntity<AuctionCategory> addCategory(@RequestBody AuctionCategory category){

        return ResponseEntity.ok(categoryService.addCategory(category));
    }
}
