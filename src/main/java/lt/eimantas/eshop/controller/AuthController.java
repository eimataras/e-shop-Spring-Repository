package lt.eimantas.eshop.controller;

import lt.eimantas.eshop.mapper.UserMapper;
import lt.eimantas.eshop.model.AuthenticationRequest;
import lt.eimantas.eshop.model.AuthenticationResponse;
import lt.eimantas.eshop.model.User;
import lt.eimantas.eshop.security.JwtTokenUtil;
import lt.eimantas.eshop.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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

        final String uid = jwtTokenUtil.validateIdTokenAndGetUid(authenticationRequest.getIdToken());
        final User user = new User(userMapper.findByUid(uid));
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails, user);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));


//  ----------------standard username and password spring boot auth using jwt
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//            );
//        } catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
//        }
//
//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());
//
//        final User user = new User(userMapper.findByUsername(authenticationRequest.getUsername()));
//
//        final String jwt = jwtTokenUtil.generateToken(userDetails, user);
//
//            return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}