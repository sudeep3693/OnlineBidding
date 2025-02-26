package xdezo.bidding.onlineBidding.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xdezo.bidding.onlineBidding.Model.User;
import xdezo.bidding.onlineBidding.Repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xdezo.bidding.onlineBidding.Validation.AddressValidation;
import xdezo.bidding.onlineBidding.Validation.UserValidation;
import xdezo.bidding.onlineBidding.Services.JWTService;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final AuthenticationManager authManager;
    private final UserRepo userRepo;
    private final UserValidation userValidation;
    private final AddressValidation addressValidation;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    public UserService(AuthenticationManager authManager, UserRepo userRepo,
                       UserValidation userValidation, AddressValidation addressValidation) {
        this.authManager = authManager;
        this.userRepo = userRepo;
        this.userValidation = userValidation;
        this.addressValidation = addressValidation;
    }

    @Transactional
    public String registerUser(User user) {
        if (!userValidation.validatePhoneNo(user.getPhoneNumber())) {
            logger.error("Invalid phone number");
            return "Invalid phone number";
        }

        if (!userValidation.validatePassword(user.getPassword())) {
            logger.error("Invalid password");
            return "Invalid password";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (!userValidation.validateEmail(user.getEmail())) {
            logger.error("Invalid email address");
            return "Invalid email address";
        }

        if (!userValidation.validateNames(user)) {
            logger.error("Invalid names");
            return "Invalid names";
        }

        if (!userValidation.isvalidRole(String.valueOf(user.getRole()))) {
            logger.error("Invalid role");
            return "Role is not valid";
        }

        if (!addressValidation.validateAddress(user)) {
            logger.error("Invalid address details");
            return "Fill the address details properly";
        }

        if (user.getAddress() != null) {
            user.getAddress().setUsers_address(user);
        } else {
            logger.error("User address is null");
            return "Address cannot be null";
        }

        userRepo.save(user);
        logger.info("User registered successfully!");
        return "User registered successfully!";
    }

    public String loginUser(User user) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );

            if (authentication.isAuthenticated()) {
                return JWTService.generateJWT(user);
            }
        } catch (Exception e) {
            logger.error("Authentication failed: " + e.getMessage());
        }
        return "Authentication failed";
    }
}
