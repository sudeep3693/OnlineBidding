package xdezo.bidding.onlineBidding.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xdezo.bidding.onlineBidding.Model.Auctions;
import xdezo.bidding.onlineBidding.Model.User;
import xdezo.bidding.onlineBidding.Services.AuctionsListedService;
import xdezo.bidding.onlineBidding.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@RestController
@RequestMapping("/api/public")
public class PublicController {

    private static final Logger logger = LoggerFactory.getLogger(PublicController.class);
    private final UserService userService;
    private final AuctionsListedService auctionsListedService;

    @Autowired
    public PublicController(UserService userService, AuctionsListedService auctionsListedService) {
        this.userService = userService;
        this.auctionsListedService = auctionsListedService;
    }

    @GetMapping("/home")
    public ResponseEntity<String> getHome() {
        logger.info("GET request received at /api/home");
        return ResponseEntity.ok("Home by Sudeep Subedi");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        logger.info("POST request received at /api/register with user: {}", user);
        String response = userService.registerUser(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        logger.info("POST request received at /api/login with user: {}", user);
        String response = userService.loginUser(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/allBids")
    public ResponseEntity<List<Auctions>> getAllItems(){

    List<Auctions> allItems =  auctionsListedService.getAllItems();
        System.out.println(allItems);
        return ResponseEntity.ok(allItems);
    }





}
