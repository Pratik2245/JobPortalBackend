package com.jobportal.Job.Portal.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {

    private final String SECRET ="82374591827364591827364591827364591827364591827364591827364591827364";
    private final long JWT_EXPIRATION = 604800000; // 7 Days

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    // get username directly
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token,Claims::getSubject);
    }

    // get expiration directly
    public Date getExpirationFromToken(String token) {
        return getClaimFromToken(token,Claims::getExpiration);
    }

    // getting the claims that is the body of the token
    public <T> T getClaimFromToken(String token, Function<Claims,T> claimResolver ){
        final Claims claims=getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Boolean isTokenExpired(String token){
        final Date expiration=getExpirationFromToken(token);
        return expiration.before(new Date());
    }
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        CustomUserDetails customUserDetails=(CustomUserDetails) userDetails;
        claims.put("id",customUserDetails.getId());
        claims.put("name",customUserDetails.getName());
        claims.put("accountType",customUserDetails.getAccountType());
        claims.put("profileId",customUserDetails.getProfileId());
        return doGenerateToken(claims,userDetails  .getUsername());
    }
    private String doGenerateToken(Map<String,Object> claims,String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_EXPIRATION))
                .signWith(getSigningKey())
                .compact();
    }
    public Boolean validateToken(String token,String username){
        final String tokenUsername=getUsernameFromToken(token);
        return (tokenUsername.equals(username)) && !isTokenExpired(token);
    }
}
