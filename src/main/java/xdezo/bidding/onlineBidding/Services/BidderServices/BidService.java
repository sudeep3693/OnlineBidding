package xdezo.bidding.onlineBidding.Services.BidderServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xdezo.bidding.onlineBidding.Model.Bids;
import xdezo.bidding.onlineBidding.Repo.BidsRepo;

@Service
public class BidService {

    @Autowired
private BidsRepo bidsRepo;

    public String addBid(Bids bid){

        return String.valueOf(bidsRepo.save(bid));

    }
}
