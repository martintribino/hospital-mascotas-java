package ttps.spring.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import ttps.spring.responses.DefaultResponse;

/**
 * Servlet Filter implementation class JWTAuthFilter
 */
@WebFilter(filterName = "jwt-auth-filter", urlPatterns = "/api/*")
public class JWTAuthFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (HttpMethod.OPTIONS.matches(req.getMethod())) {
            chain.doFilter(request, response);
            return;
        }
        String token = JWToken.getToken(req);
        if (token == null) {
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
            res.setContentType("application/json");
            PrintWriter out = response.getWriter();
            DefaultResponse defResp = new DefaultResponse(
            		HttpStatus.UNAUTHORIZED,
            		HttpStatus.UNAUTHORIZED.value(),
            		"No token"
        		);
			out.print(defResp);
            return;
        }
		try {
			JWToken.validateToken(token);
		} catch  (ExpiredJwtException exp) {
            res.setStatus(HttpStatus.FORBIDDEN.value());
            res.setContentType("application/json");
            PrintWriter out = response.getWriter();
            DefaultResponse defResp = new DefaultResponse(
            		HttpStatus.FORBIDDEN,
            		HttpStatus.FORBIDDEN.value(),
            		"Token expired: " + exp.getMessage()
        		);
			out.print(defResp.toJSON());
            return;
		} catch (JwtException e) {
            res.setStatus(HttpStatus.FORBIDDEN.value());
            res.setContentType("application/json");
            PrintWriter out = response.getWriter();
            DefaultResponse defResp = new DefaultResponse(
            		HttpStatus.FORBIDDEN,
            		HttpStatus.FORBIDDEN.value(),
            		"Error in token: " + e.getMessage()
        		);
			out.print(defResp.toJSON());
            return;
		}
        chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
