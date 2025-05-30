package AIYA.com.FAIN;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
		info = @io.swagger.v3.oas.annotations.info.Info(title = "FAIN API", version ="v1"),
		servers = {
				@Server(url="https://fain-aiya.shop",description="배포서버")
		}
)
@SpringBootApplication
public class FainApplication {

	public static void main(String[] args) {
		SpringApplication.run(FainApplication.class, args);
	}

}
