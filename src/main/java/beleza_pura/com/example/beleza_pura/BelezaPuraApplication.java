package beleza_pura.com.example.beleza_pura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "beleza_pura.com.example.beleza_pura",
    "beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers"
})
public class BelezaPuraApplication {
    public static void main(String[] args) {
        SpringApplication.run(BelezaPuraApplication.class, args);
    }
}