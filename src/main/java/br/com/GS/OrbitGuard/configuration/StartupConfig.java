package br.com.GS.OrbitGuard.configuration;

import br.com.GS.OrbitGuard.service.OrbitGuardService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartupConfig {

    @Bean
    CommandLineRunner init(OrbitGuardService service) {

        return args -> {
            service.importarFocos();
        };
    }
}
