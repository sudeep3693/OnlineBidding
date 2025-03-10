package xdezo.bidding.onlineBidding.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import xdezo.bidding.onlineBidding.Payment.Models.PaymentsInfo;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

//        @Autowired
//        private SecretKey secretKey;

    @Value("${esewa.submissionUrl}")
    private String esewaPaymentUrl;
//
//    @Value("${esewa.failure_url}")
//    private String failureUrl;
//
//    @Value("${esewa.success_url}")
//    private String successUrl;

    @PostMapping("/pay")
    public ResponseEntity<String> initiatePayment(@RequestBody PaymentsInfo paymentsInfo) {
        // Create request body

//        String transaction_uuid  =String.valueOf(UUID.randomUUID());
//        String signed_field_names = String.valueOf(paymentsInfo.getAmount()).concat(",").concat(transaction_uuid).concat(",").concat(paymentsInfo.getProductCode());
//        String signature = secretKey.generateSignature(String.valueOf(paymentsInfo.getTotalAmount()), transaction_uuid, paymentsInfo.getProductCode());

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("amount", paymentsInfo.getAmount());
        requestBody.put("failure_url", paymentsInfo.getFailureUrl());
        requestBody.put("transaction_uuid",paymentsInfo.getTransactionUuid());
        requestBody.put("signed_field_names",paymentsInfo.getSignedFieldNames());
        requestBody.put("signature",paymentsInfo.getSignature());
        requestBody.put("success_url", paymentsInfo.getSuccessUrl());
        requestBody.put("product_delivery_charge", paymentsInfo.getProductDeliveryCharge());
        requestBody.put("product_service_charge", paymentsInfo.getProductServiceCharge());
        requestBody.put("total_amount", paymentsInfo.getTotalAmount());
        requestBody.put("tax_amount", paymentsInfo.getTaxAmount());
        requestBody.put("product_code", paymentsInfo.getProductCode());

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create HTTP entity with headers and body
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

            // Make the POST request
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(esewaPaymentUrl, HttpMethod.POST, requestEntity, String.class);

            // Return eSewa's response
            System.out.println(response);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            log.error("error in catch block {}",e);
            return null;
        }
    }
}
