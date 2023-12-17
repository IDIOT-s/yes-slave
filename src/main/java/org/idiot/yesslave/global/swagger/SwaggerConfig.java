package org.idiot.yesslave.global.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI serverApiConfig() {
        return new OpenAPI()
                .info(new Info().title("YES SALVE API")
                        .description("YES SLAVE API SWAGGER UI입니다."));
    }
}
