package lt.eimantas.eshop.controller;

import com.google.firebase.auth.FirebaseAuth;
import lt.eimantas.eshop.mapper.UserMapper;
import lt.eimantas.eshop.model.AuthenticationRequest;
import lt.eimantas.eshop.model.AuthenticationResponse;
import lt.eimantas.eshop.model.User;
import lt.eimantas.eshop.security.JwtTokenUtil;
import lt.eimantas.eshop.security.MyUserDetailsService;
import lt.eimantas.eshop.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserMapper userMapper;


    @RequestMapping(value = "/api/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

//  ----------------Creating jwt
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final User user = new User(userMapper.findByUsername(authenticationRequest.getUsername()));

        final String jwt = jwtTokenUtil.generateToken(userDetails, user);


//  ----------------Creating fireBase customToken
        final String uid = String.valueOf(user.getUser_id());
        final String customToken = FirebaseAuth.getInstance().createCustomToken(uid);


        return ResponseEntity.ok(new AuthenticationResponse(jwt, customToken));
    }
}