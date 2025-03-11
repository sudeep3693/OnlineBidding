package xdezo.bidding.onlineBidding.Controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xdezo.bidding.onlineBidding.Payment.Models.PaymentsInfo;
import xdezo.bidding.onlineBidding.Payment.Service.EsewaPaymentService;

@Slf4j
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private EsewaPaymentService esewaPaymentService;

    @PostMapping("/pay")
    public ResponseEntity<String> initiatePayment(@RequestBody @Valid PaymentsInfo paymentsInfo) {
        try {
            return ResponseEntity.ok(esewaPaymentService.initiateEsewaPayment(paymentsInfo).getBody());
        } catch (Exception e) {
            log.error("Error in payment initiation: ", e);
            return ResponseEntity.internalServerError().body("Payment initiation failed");
        }
    }

    @GetMapping("/success")
    public ResponseEntity<String> success(){

        System.out.println("success");
       return ResponseEntity.ok("success");

    }

    @GetMapping("failed")
    public ResponseEntity<String> failed(){
        System.out.println("Failed");
        return ResponseEntity.ok("failed");
    }
}
