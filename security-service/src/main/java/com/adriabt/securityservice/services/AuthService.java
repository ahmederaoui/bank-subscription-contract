package com.adriabt.securityservice.services;

import com.adriabt.securityservice.dtos.AuthDto;
import com.adriabt.securityservice.dtos.VerificationRequest;
import com.adriabt.securityservice.exceptions.AgentNotFound;
import com.adriabt.securityservice.exceptions.InvalidTotp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final AgentService agentService;
    public Map<String, String> getJwtToken(AuthDto authDto) throws AgentNotFound, InvalidTotp {
        String subject=null;
        String scope=null;
        Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword())
            );
        subject=authentication.getName();
        scope=authentication.getAuthorities()
                    .stream().map(aut -> aut.getAuthority())
                    .collect(Collectors.joining(" "));
        VerificationRequest verificationRequest=new VerificationRequest(authDto.getUsername(), authDto.getCode());
        System.out.println(authDto.getCode());
        boolean isTotpValid=agentService.verifyTotp(verificationRequest);
        System.out.println(isTotpValid);
        if (!isTotpValid) throw new InvalidTotp("Totp invalid");
        Map<String, String> idToken=new HashMap<>();
        Instant instant=Instant.now();
        JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(30, ChronoUnit.MINUTES))
                .issuer("security-service")
                .claim("scope",scope)
                .build();
        String jwtAccessToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        idToken.put("accessToken",jwtAccessToken);
        System.out.println(idToken);

        return idToken;
    }
}
