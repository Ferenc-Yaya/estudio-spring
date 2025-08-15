package pe.estudio.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc  // ← Esto habilita Spring MVC
@ComponentScan(basePackages = "pe.estudio.spring")  // ← Buscar @Controller, @Service, etc.
public class WebConfig implements WebMvcConfigurer {

    public WebConfig() {
        System.out.println("🌐 Creando WebConfig - Configuración Spring MVC");
    }

    // Configurar dónde están los archivos JSP
    @Bean
    public ViewResolver viewResolver() {
        System.out.println("📄 Configurando ViewResolver para JSP");

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");   // Carpeta donde están las vistas
        resolver.setSuffix(".jsp");              // Cambiar a .jsp
        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);

        return resolver;
    }
}
