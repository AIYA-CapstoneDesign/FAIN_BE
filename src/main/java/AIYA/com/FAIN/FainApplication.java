package AIYA.com.FAIN;


import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "AIYA.com.FAIN")
public class FainApplication {

	public static void main(String[] args) {
		SpringApplication.run(FainApplication.class, args);
	}
	@PostConstruct
	public void setTimeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}
}
