package com.chatop.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import com.chatop.model.UserEntity;

@Service
public class JWTService {

        private final JwtEncoder jwtEncoder;

        @Autowired
        private int jwtKeyExpiracy;
        
	
	public JWTService(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}
	
        /**
         * generate a new token for user
         * @param a user entity
         * @return a new token
         */
	public String generateToken(UserEntity user) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(jwtKeyExpiracy, ChronoUnit.DAYS))
                .subject(user.getEmail())
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }
	
}