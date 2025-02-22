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


@Service
public class UserService {
    @Autowired
    UserValidation userValidation;
    @Autowired
    AddressValidation addressValidation;
    private final Logger logger = LoggerFactory.getLogger(UserService.class.getName());
    private final AuthenticationManager authManager;
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public UserService(AuthenticationManager authManager, UserRepo userRepo) {
        this.authManager = authManager;
        this.userRepo = userRepo;

    }

    @Transactional
    public String registerUser(User user) {


        if(userValidation.validateUsername(user.getUsername()))
        {
            if(userValidation.validatePhoneNo(user.getPhoneNumber())){

                if(userValidation.validatePassword(user.getPassword())){
                    user.setPassword(passwordEncoder.encode(user.getPassword()));

                    if(userValidation.validateEmail(user.getEmail())){

                        if(userValidation.validateNames(user)){


                            if(userValidation.isvalidRole(String.valueOf(user.getRole()))){

                                if (addressValidation.validateAddress(user)) {
                                    user.getAddress().setUsers_address(user);  // Ensure the relationship is set
                                    userRepo.save(user);
                                    logger.info("User registered successfully!");
                                    return "User registered successfully!";
                                }
                                else{
                                    logger.error("Address is null");
                                    return "Fill the address details properly";
                                }
                            }

                            else{

                                logger.error("role not valid");
                                return "role is not valid";
                            }



                        }
                        else{
                            logger.error("Invalid names");
                            return "invalid names";
                        }
                    }
                    else{
                        logger.error("Invalid email address");
                        return "invalid email address";
                    }
                }
                else{
                    logger.error("Invalid password");
                    return "invalid password";
                }
            }
            else{
                logger.error("Invalid phone number");
                return "invalid phone number";
            }

        }
        else{
            logger.error("Username already exists");
            return "Username already exists";
        }

    }

    public String loginUser(User user){
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));


        if(authentication.isAuthenticated()){
           return JWTService.generateJWT(user);
        }
        else {
            logger.error("Authentication Failed");
            return "Fail";
        }
    }
}
