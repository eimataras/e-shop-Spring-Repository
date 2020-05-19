package lt.eimantas.eshop.security;

import lt.eimantas.eshop.reqResLogger.MyHttpServletRequestWrapper;
import lt.eimantas.eshop.reqResLogger.MyHttpServletResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//-------Tokens' validation job start here
        final String authorizationHeader = request.getHeader("Authorization");
        final String firebaseHeader = request.getHeader("Firebase");
        String username = null;
        String jwt = null;
        String idToken = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtTokenUtil.extractUsername(jwt);
        }

        if (firebaseHeader != null && firebaseHeader.startsWith("Bearer ")) {
            idToken = firebaseHeader.substring(7);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null && idToken != null) {
            UserDetailsService userDetailsService;
            UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(jwt, userDetails) && jwtTokenUtil.isIdTokenValid(idToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
//-------Tokens' validation job end here


//-------REST API request/response logging job start here
        if (response.getCharacterEncoding() == null) {
            response.setCharacterEncoding("UTF-8"); // Or whatever default. UTF-8 is good for World Domination.
        }

        MyHttpServletRequestWrapper myHttpServletRequestWrapper = new MyHttpServletRequestWrapper(request);
        MyHttpServletResponseWrapper myHttpServletResponseWrapper = new MyHttpServletResponseWrapper(response);

        filterChain.doFilter(myHttpServletRequestWrapper, response); //myHttpServletResponseWrapper does the job, but fucks up the response headers logging, that's why it is not included into the filterChain
//        myHttpServletResponseWrapper.flushBuffer();

        byte[] copy = myHttpServletResponseWrapper.getCopy();
        Scanner sc = new Scanner(myHttpServletRequestWrapper.getInputStream()).useDelimiter("\\A");
        String result = sc.hasNext() ? sc.next() : "";

        log.info("");
        log.info("--------Request method: [" + myHttpServletRequestWrapper.getMethod() + "] ------------------------");
        log.info("URI: [" + myHttpServletRequestWrapper.getRequestURI() + "]");
        Collection<String> reqHeaderNames = Collections.list(myHttpServletRequestWrapper.getHeaderNames());
        reqHeaderNames.forEach(name -> {
            log.info("Request header: [" + name + ": " + myHttpServletRequestWrapper.getHeader(name) + "]");
        });
        log.info("Request body: " + result);

        log.info("--------Response status code: [" + response.getStatus() + "] ------------------");
        Collection<String> resHeaderNames = response.getHeaderNames();
        if (resHeaderNames != null) {
            resHeaderNames.forEach(name -> {
                log.info("Response header: [" + name + ": " + response.getHeader(name) + "]");
            });
        }
        log.info("Response body: " + new String(copy, myHttpServletResponseWrapper.getCharacterEncoding()));
        log.info("");
//-------REST API request/response logging job end here
    }
}