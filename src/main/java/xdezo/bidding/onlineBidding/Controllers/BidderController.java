package xdezo.bidding.onlineBidding.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xdezo.bidding.onlineBidding.Model.Bids;
import xdezo.bidding.onlineBidding.Repo.BidsRepo;
import xdezo.bidding.onlineBidding.Services.BidderServices.BidService;

@RestController
@RequestMapping("/api/bidder")
public class BidderController {

@Autowired
    private  BidService bidService;

    public BidderController(){

    }

    @PostMapping("/addBid")
    public ResponseEntity<String> addBid(@RequestBody Bids bid){

    return ResponseEntity.ok(bidService.addBid(bid));
    }
}
