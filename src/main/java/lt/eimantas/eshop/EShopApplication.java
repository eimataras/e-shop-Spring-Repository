package lt.eimantas.eshop;

import lt.eimantas.eshop.model.Book;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@MappedTypes(Book.class)
@MapperScan("lt.eimantas.eshop/mapper")
@SpringBootApplication
public class EShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EShopApplication.class, args);
	}

}
