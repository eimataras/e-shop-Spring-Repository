//package lt.eimantas.eshop.security;
//
//import com.auth0.jwt.JWT;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lt.eimantas.eshop.model.LoginViewModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//
//import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
//
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        // Grab credentials and map them to login viewmodel
//        LoginViewModel credentials = null;
//        try {
//            credentials = new ObjectMapper().readValue(request.getInputStream(), LoginViewModel.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Create login token
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                credentials.getUsername(),
//                credentials.getPassword(),
//                new ArrayList<>());
//
//        // Authenticate user
//        Authentication auth = authenticationManager.authenticate(authenticationToken);
//
//        return auth;
//    }
//
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        // Grab principal
//        MyUserPrincipal principal = (MyUserPrincipal) authResult.getPrincipal();
//
//        // Create JWT Token
//        String token = JWT.create()
//                .withSubject(principal.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
//                .sign(HMAC512(JwtProperties.SECRET.getBytes()));
//
//        // Add token in response
//        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX +  token);
//    }
//}