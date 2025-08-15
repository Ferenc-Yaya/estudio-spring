package pe.estudio.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // ← ESTA SOLA ANOTACIÓN hace TODA la configuración
public class PokemonSpringBootApplication {

    public static void main(String[] args) {
        System.out.println("🚀 Iniciando Pokemon Spring Boot App...");

        // UNA LÍNEA arranca toda la aplicación
        SpringApplication.run(PokemonSpringBootApplication.class, args);

        System.out.println("✅ ¡Pokemon Spring Boot App iniciada!");
    }
}

/*
¿Qué hace @SpringBootApplication automáticamente?

@SpringBootApplication =
    @Configuration        (configura Spring automáticamente)
  + @EnableAutoConfiguration (detecta qué necesitas y lo configura)
  + @ComponentScan      (busca tus @Controller, @Service, etc.)

¡Todo lo que hiciste manualmente en 100+ líneas de configuración!
*/
