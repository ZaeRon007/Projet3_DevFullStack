package com.chatop.configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    

    @Bean
	public SecretKey jwtSecretKey(@Value("${security.jwt.secret-key}") String secret) {
                byte[] keyBytes = secret.getBytes();
                return new SecretKeySpec(keyBytes, "HmacSHA256");
	}

    @Bean
    public int jwtKeyExpiracy(@Value("${security.jwt.expiration-time}") int time){
        return time;
    }
}
