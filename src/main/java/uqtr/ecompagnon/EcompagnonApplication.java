package uqtr.ecompagnon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;

//@ConfigurationPropertiesScan
@SpringBootApplication
@EnableAsync
//@SecurityScheme(name = "ecompagnon-api", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
//@OpenAPIDefinition(info = @Info(title = "User API", version = "2.0", description = "User Details"))
public class EcompagnonApplication {

    public static void main(String[] args) {


        SpringApplication.run(EcompagnonApplication.class, args);

    }

}
