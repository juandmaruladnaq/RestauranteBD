package uam.bd.restaurante.BD;

import java.sql.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import uam.bd.restaurante.BD.Model.Usuario;

@Service
public class JwtTokenService 
{

    private final static int tokenExpirationTime = 30 * 60 * 1000;
    
    private final static String tokenKey = "ut1FfO9sSPjG1OKxVh";
    

    public static String generateToken(Usuario user) 
    {    	
    	return Jwts.builder()
				.setSubject(user.getCedula())
				.claim("user", user)
				.signWith(SignatureAlgorithm.HS256, tokenKey)
				.setIssuedAt(new Date(System.currentTimeMillis()))					
				.compact();
    }

    public static void verifyToken(String token) throws JwtException 
    {
        Jwts.parser()
                .setSigningKey(tokenKey)
                .parse(token);
    }

    public static Claims getClaimsFromToken(String token) 
    {
        return Jwts.parser()
                .setSigningKey(tokenKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public static String updateExpirationDateToken(String token) 
    {
        Claims claims = getClaimsFromToken(token);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpirationTime))
                .signWith(SignatureAlgorithm.HS512, tokenKey)
                .compact();
    }
}
