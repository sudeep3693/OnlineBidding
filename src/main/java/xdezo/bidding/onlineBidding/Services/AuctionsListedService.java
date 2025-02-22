package xdezo.bidding.onlineBidding.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xdezo.bidding.onlineBidding.Model.Auctions;
import xdezo.bidding.onlineBidding.Repo.AuctionsRepo;

import java.util.List;

@Service
public class AuctionsListedService {

    private Auctions items;
    @Autowired
    private AuctionsRepo itemsRepo;

    public List<Auctions> getAllItems(){

        return itemsRepo.findAll();
    }

}
