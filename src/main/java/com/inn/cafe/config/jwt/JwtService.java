package com.inn.cafe.config.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.inn.cafe.utils.environments.ReadEnvironmentUtils;
import com.inn.cafe.utils.parsing.TimeParserUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

  private static final String SECRET_KEY = "JWT_SECRET_KEY";
  private static final String ACCESS_DURATION = "JWT_ACCESS_DURATION";
  
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> extraClaims = new HashMap<>();

    extraClaims.put("Role", userDetails.getAuthorities());
    return generateToken(extraClaims, userDetails);
  }

  public String generateToken(
    Map<String, Object> extraClaims,
    UserDetails userDetails
  ) {
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(access_duration(
                ReadEnvironmentUtils.readProperty(ACCESS_DURATION)
              )
            ))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token); 
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
            .parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
  }

  private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(
      ReadEnvironmentUtils.readProperty(SECRET_KEY)
    );
    return Keys.hmacShaKeyFor(keyBytes);
  }
  
  private long access_duration(String duration) {
    return System.currentTimeMillis() + TimeParserUtils.ParseDurationToMillis(duration);
  }

  
}
