package lt.eimantas.eshop.security;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FireBaseConfig {

    @Bean
    FirebaseApp createFireBaseApp() throws IOException {

        InputStream serviceAccount =
                 new ClassPathResource("bookshop-86079-firebase-adminsdk-74u5r-134131e90c.json").getInputStream();

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://bookshop-86079.firebaseio.com")
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
