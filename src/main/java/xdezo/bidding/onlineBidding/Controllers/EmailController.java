package xdezo.bidding.onlineBidding.Controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xdezo.bidding.onlineBidding.Emails.EmailService;
import xdezo.bidding.onlineBidding.Emails.Entities.MailMessage;

@RestController
@RequestMapping("/app/mail")
@Slf4j
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody MailMessage mailMessage){

        try{
           return ResponseEntity.ok(emailService.sendSimpleMail(mailMessage));

        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        }
    }



}
