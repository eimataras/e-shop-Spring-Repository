package lt.eimantas.eshop.model;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;
    private String customToken;


    public AuthenticationResponse (String jwt) {
        this.jwt = jwt;
    }

    public AuthenticationResponse (String jwt, String customToken) {
        this.jwt = jwt;
        this.customToken = customToken;
    }
}
