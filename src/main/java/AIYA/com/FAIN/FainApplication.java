package AIYA.com.FAIN;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(servers = {
		@Server(url="https://fain-aiya.shop",description = "Default Server Url")
})
@SpringBootApplication
public class FainApplication {

	public static void main(String[] args) {
		SpringApplication.run(FainApplication.class, args);
	}

}
