package xdezo.bidding.onlineBidding.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xdezo.bidding.onlineBidding.Model.User;
import xdezo.bidding.onlineBidding.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api") // Common base path for endpoints
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final UserService userService;
//    private final TimeApi timeApi;
    @Autowired
    public HomeController(UserService userService/*, TimeApi timeApi*/) {
        this.userService = userService;
//        this.timeApi = timeApi;
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

//    @GetMapping("/time")
//    public String getTime(){
//       String str = timeApi.getTime();
//        System.out.println(str);
//       return str;
//    }
}
