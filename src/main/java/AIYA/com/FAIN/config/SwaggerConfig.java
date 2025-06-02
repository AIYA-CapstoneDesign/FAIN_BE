package AIYA.com.FAIN.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
    name = "BearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class SwaggerConfig {
  private static final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);

  @Bean
  public OpenAPI openAPI() {
    log.info("✅ SwaggerConfig:OpenAPI Bean 생성됨!");

    return new OpenAPI()
        .components(new Components())
        .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
        .info(new Info()
            .title("FAIN Swagger")
            .description("FAIN REST API")
            .version("1.0.0"))
        .servers(List.of(
            new Server().url("https://fain-aiya.shop").description("FAIN배포서버")
        ));
  }
}
