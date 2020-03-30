package lt.eimantas.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MappedTypes(Book.class)
//@MapperScan("lt.eimantas.eshop/mapper")
//@ComponentScan("lt.eimantas.eshop/security")
//@EnableWebMvc
@SpringBootApplication
public class EShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EShopApplication.class, args);
	}

}