package lt.eimantas.eshop.model;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;


    public AuthenticationResponse (String jwt) {
        this.jwt = jwt;
    }
}
