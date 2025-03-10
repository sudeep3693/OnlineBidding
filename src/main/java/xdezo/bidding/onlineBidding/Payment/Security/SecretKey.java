package xdezo.bidding.onlineBidding.Payment.Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class SecretKey {


        @Value("${esewa.secretKey}")
        private String secret;

        public String generateSignature(String totalAmount, String transactionUuid, String productCode) {
            try {
                if (secret == null || secret.isEmpty()) {
                    throw new IllegalArgumentException("Secret key is missing");
                }

                // Construct the message to sign (order matters)
                String message = totalAmount + "," + transactionUuid + "," + productCode;

                Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
                SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
                sha256_HMAC.init(secretKeySpec);

                byte[] hashBytes = sha256_HMAC.doFinal(message.getBytes("UTF-8"));
                return Base64.getEncoder().encodeToString(hashBytes);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

