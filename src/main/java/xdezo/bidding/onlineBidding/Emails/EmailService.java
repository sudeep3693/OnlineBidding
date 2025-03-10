package xdezo.bidding.onlineBidding.Emails;


import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import xdezo.bidding.onlineBidding.Emails.Entities.MailMessage;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendSimpleMail(MailMessage mailMessage){

        try{

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);


            String to = mailMessage.getTo();
            String htmlContent = mailMessage.getMessage();
            String subject = mailMessage.getSubject();

            helper.setFrom("sudeepsubedi72@gmail.com");
            helper.setTo(to);
            helper.setText(htmlContent,true);
            helper.setSubject(subject);


            javaMailSender.send(message);
            return "success";
        }
        catch (Exception e){
            log.error("error in email service");
            return "failed";
        }
    }
}
