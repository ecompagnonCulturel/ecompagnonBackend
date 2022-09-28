package uqtr.ecompagnon.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import javax.crypto.SecretKey;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")//Varaible créee dans application-dev.properties
public class JwtConfig {
    private String secretKey;
    private String tokenPrefix;
    private long tokenExpirationAfterDays;

    public JwtConfig() {
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public void setTokenExpirationAfterDays(long tokenExpirationAfterDays) {
        this.tokenExpirationAfterDays = tokenExpirationAfterDays;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public Long getTokenExpirationAfterDays() {
        return tokenExpirationAfterDays;
    }


    public String getAuthorizationHeader()
    {
        return HttpHeaders.AUTHORIZATION;
    }
}
