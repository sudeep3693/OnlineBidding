package xdezo.bidding.onlineBidding.Controllers;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xdezo.bidding.onlineBidding.Model.Bids;
import xdezo.bidding.onlineBidding.Services.BidderServices.BidService;

@RestController
@RequestMapping("/api/bidder")
@Slf4j
public class BidderController {

@Autowired
    private  BidService bidService;


    @PostMapping("/addBid")
    public ResponseEntity<String> addBid(@Valid @RequestBody Bids bid){
try{
    return ResponseEntity.ok(bidService.addBid(bid));

}
catch(Exception e){
    System.out.println(e);

    return null;
}
    }
}
