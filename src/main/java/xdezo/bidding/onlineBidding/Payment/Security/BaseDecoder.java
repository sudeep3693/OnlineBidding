package xdezo.bidding.onlineBidding.Payment.Security;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class BaseDecoder {

    public String decodeBase64(String encodedData) {

        byte[] decodedBytes = Base64.getDecoder().decode(encodedData);
        return new String(decodedBytes);
    }
}
