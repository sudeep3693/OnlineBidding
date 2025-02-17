package xdezo.bidding.onlineBidding.Services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import xdezo.bidding.onlineBidding.Model.User;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JWTService {
    private static final Key secret = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final JwtParser jwtParser = Jwts.parser().verifyWith((SecretKey) secret).build();


    public static String generateJWT(User user  ) {

        return Jwts.builder()
                .subject(user.getUsername())
                .issuer("Online Bidding System")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60))//for 1 hour
                .signWith(secret)
                .compact();
    }

    public String getUserName(String token) {
        return jwtParser.parseClaimsJws(token).getPayload().getSubject();

    }

    public boolean validateToken(String token, String username) {
        try {
            Jws<Claims> claims = jwtParser.parseSignedClaims(token);
            return username.equals(claims.getPayload().getSubject()) && !isTokenExpired(token);
        } catch (Exception e) {
            return false; // Invalid token
        }
    }

    private boolean isTokenExpired(String token) {
        return jwtParser.parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }
}
