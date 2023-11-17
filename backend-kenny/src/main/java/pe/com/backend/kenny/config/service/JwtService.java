package pe.com.backend.kenny.config.service;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	
	private static final String LLAVE_PRIVADA = "AEA";
	
	private static final Long TIEMPO_EXPIRACION = 1800 * 60 * (long) 100;
	
	
	
	public String obtenerToken(Map<String, Object> claims ,UserDetails user)
	{
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TIEMPO_EXPIRACION))
				.signWith(SignatureAlgorithm.HS256, encriptarBase64())
				.compact();
	}
	
	private String encriptarBase64()
	{
		return Base64.getEncoder().encodeToString(LLAVE_PRIVADA.getBytes());
	}
	
	private Claims obtenerTodoClaims(String token)
	{
		return Jwts.parser().setSigningKey(encriptarBase64()).parseClaimsJws(token).getBody();
	}
	
	public String obtenerUsername(String token)
	{
		return this.obtenerTodoClaims(token).getSubject();
	}
	
	private boolean validarExpiracion(String token)
	{
		return this.obtenerTodoClaims(token).getExpiration().before(new Date());
	}
	
	public boolean validarToken(String token, UserDetails userDetails)
	{
		final String username = this.obtenerUsername(token);
		return (userDetails.getUsername().equals(username) && !validarExpiracion(token));
	}
}
