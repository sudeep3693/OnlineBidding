package xdezo.bidding.onlineBidding.Validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xdezo.bidding.onlineBidding.Enums.UserRoles;
import xdezo.bidding.onlineBidding.Model.User;
import xdezo.bidding.onlineBidding.Repo.UserRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

@Component
public class UserValidation {

    private final UserRepo userRepo;
    private final Logger logger = LoggerFactory.getLogger(UserValidation.class);

    @Autowired
    public UserValidation(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean validatePhoneNo(String phoneNo) {
        return phoneNo != null && phoneNo.matches("\\d{10}");
    }

    public boolean validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            logger.error("Username is empty");
            return false;
        }
        if (username.length() < 5) {
            logger.error("Username is too short");
            return false;
        }
        if (userRepo.findByUsername(username) != null) {
            logger.error("Username already exists");
            return false;
        }
        return true;
    }

    public boolean validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            logger.error("Password is empty");
            return false;
        }
        if (password.length() < 5) {
            logger.error("Password is too short");
            return false;
        }
        logger.info("Password is valid");
        return true;
    }

    public boolean validateEmail(String emailAddress) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            logger.error("Email address is empty");
            return false;
        }

        if(userRepo.findByEmail(emailAddress)!=null){
            logger.error("Email is already used");
            return false;
        }

        String regexPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        boolean isValid = Pattern.compile(regexPattern).matcher(emailAddress).matches();

        if (!isValid) {
            logger.error("Invalid email format");
        }
        return isValid;
    }

    public boolean validateNames(User user) {
        if (user == null || user.getFirstName() == null || user.getLastName() == null) {
            logger.error("First name and last name cannot be null");
            return false;
        }
        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty()) {
            logger.error("First name and last name cannot be empty");
            return false;
        }
        if (user.getFirstName().length() < 2 || user.getLastName().length() < 2) {
            logger.error("Invalid first name or last name");
            return false;
        }
        logger.info("Valid name");
        return true;
    }



    public boolean isvalidRole(String role){

        if(role.isEmpty()){

            System.out.println("user role cannot be empty");
            logger.error("empty user role");
            return false;
        }
        else{
            for (UserRoles s : UserRoles.values()) {
                if (s.name().equalsIgnoreCase(role)) { // Case-insensitive match
                    logger.info("valid user role");
                    return true;
                }
                else{
                    logger.error("Role not found");
                    return false;
                }

                }

        }

        logger.error("Role not found");
        return false; // Return false if no match is found
    }
}
