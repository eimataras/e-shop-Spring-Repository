package lt.eimantas.eshop.model;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String idToken;

    public AuthenticationRequest () {
    }

    public AuthenticationRequest (String idToken) {
        this.idToken = idToken;
    }
}
