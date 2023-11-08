package pe.com.backend.kenny.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import pe.com.backend.kenny.config.service.JwtService;
import pe.com.backend.kenny.exception.AuthorizationNoEncontradoException;

@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private @Autowired JwtService jwtService;
	
	@Qualifier("userDetailsServiceImpl")
	private @Autowired UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		final String authorizationHeader = request.getHeader("Authorization");
		
		String username;
		String jwt;
		
		if(authorizationHeader == null)
		{
			filterChain.doFilter(request, response);
            return;
		}
		if(!authorizationHeader.startsWith("Bearer "))
		{
			filterChain.doFilter(request, response);
            return;
		}
		jwt = authorizationHeader.substring(7);
		username = jwtService.obtenerUsername(jwt);
		
		
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if(jwtService.validarToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
	                    userDetails,
	                    null,
	                    userDetails.getAuthorities());
				authToken.setDetails(userDetails);
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
				
			}
		}
		filterChain.doFilter(request, response);
	}

}
