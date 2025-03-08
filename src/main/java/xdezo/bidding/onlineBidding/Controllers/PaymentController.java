package xdezo.bidding.onlineBidding.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import xdezo.bidding.onlineBidding.Payment.Models.PaymentsInfo;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Value("${esewa.submissionUrl}")
    private String esewaPaymentUrl;

    @Value("${esewa.failure_url}")
    private String failureUrl;

    @Value("${esewa.success_url}")
    private String successUrl;

    @PostMapping("/pay")
    public ResponseEntity<String> initiatePayment(@RequestBody PaymentsInfo paymentsInfo) {
        // Create request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("amount", paymentsInfo.getAmount());
        requestBody.put("failure_url", failureUrl);
        requestBody.put("success_url", successUrl);
        requestBody.put("product_delivery_charge", paymentsInfo.getDeliveryCharge());
        requestBody.put("product_service_charge", paymentsInfo.getServiceCharge());
        requestBody.put("total_amount", paymentsInfo.getTotalAmount());
        requestBody.put("tax_amount", paymentsInfo.getTaxAmount());
        requestBody.put("product_code", paymentsInfo.getProductCode());

        // Set HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HTTP entity with headers and body
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make the POST request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(esewaPaymentUrl, HttpMethod.POST, requestEntity, String.class);

        // Return eSewa's response
        return ResponseEntity.ok(response.getBody());
    }
}
