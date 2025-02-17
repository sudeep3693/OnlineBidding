package xdezo.bidding.onlineBidding.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
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

    public HomeController(UserService userService) {
        this.userService = userService;
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
}
