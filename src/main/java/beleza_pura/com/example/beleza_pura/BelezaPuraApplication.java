package beleza_pura.com.example.beleza_pura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities")
@EnableJpaRepositories("beleza_pura.com.example.beleza_pura.infrastructure.persistence.repositories")
public class BelezaPuraApplication {
    public static void main(String[] args) {
        SpringApplication.run(BelezaPuraApplication.class, args);
    }
}