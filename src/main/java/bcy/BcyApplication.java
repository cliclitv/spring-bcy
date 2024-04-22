package bcy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("bcy.mapper")
public class BcyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BcyApplication.class, args);
	}

}
