package com.hemanshu.expensetracker.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import com.hemanshu.expensetracker.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AuthFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String authHeader = httpRequest.getHeader("Authorization");

		if (authHeader != null) {
			String[] authHeaderArr = authHeader.split("Bearer ");

			if (authHeaderArr.length > 1 && authHeaderArr[1] != null) {
				String token = authHeaderArr[1];

				try {
					Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY).parseClaimsJws(token)
							.getBody();
					int userId = Integer.parseInt(claims.get("userId").toString());
					httpRequest.setAttribute("userId", userId);
				} catch (Exception e) {
					httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid/Expired token");
					return;
				}
			} else {
				httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be beare[token]");
				return;
			}
		} else {
			httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be provided");
			return;
		}
		chain.doFilter(request, response); // continue the process
	}
}
