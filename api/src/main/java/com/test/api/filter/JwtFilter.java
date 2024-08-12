package com.test.api.filter;

import com.test.api.JwtDomain.JwtAuthentication;
import com.test.api.service.JwtProvider;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            jakarta.servlet.http.HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String token = null;
        final String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            token = bearer.substring(7);
        }

        if (token != null && jwtProvider.validateAccessToken(token)) {
            final Claims claims = jwtProvider.getAccessClaims(token);
            final JwtAuthentication jwtAuthentication = new JwtAuthentication();
            jwtAuthentication.setLogin(claims.getSubject());
            jwtAuthentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
        }
        filterChain.doFilter(request, response);
    }

}


//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class JwtFilter extends OncePerRequestFilter {
//
//    private static final String AUTHORIZATION = "Authorization";
//    private final JwtProvider jwtProvider;
//
//    @Override
//    protected void doFilterInternal(
//            jakarta.servlet.http.HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain) throws ServletException, IOException {
//
//        final String token = getTokenFromRequest((HttpServletRequest) request);
//        if (token != null && jwtProvider.validateAccessToken(token)) {
//            final Claims claims = jwtProvider.getAccessClaims(token);
//            final JwtAuthentication jwtAuthentication = new JwtAuthentication();
//            jwtAuthentication.setLogin(claims.getSubject());
//            jwtAuthentication.setAuthenticated(true);
//            SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
//        }
//        filterChain.doFilter(request, response);
//    }
//
////    @Override
////    protected void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
////            throws IOException, ServletException {
////        final String token = getTokenFromRequest((HttpServletRequest) request);
////        if (token != null && jwtProvider.validateAccessToken(token)) {
////            final Claims claims = jwtProvider.getAccessClaims(token);
////            final JwtAuthentication jwtAuthentication = new JwtAuthentication();
////            jwtAuthentication.setLogin(claims.getSubject());
////            jwtAuthentication.setAuthenticated(true);
////            SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
////        }
////        fc.doFilter(request, response);
////    }
//
//    private String getTokenFromRequest(HttpServletRequest request) {
//        final String bearer = request.getHeader(AUTHORIZATION);
//        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
//            return bearer.substring(7);
//        }
//        return null;
//    }
//
//}