package pe.estudio.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "pe.estudio.spring")
public class Configuracion {
    public Configuracion(){
        System.out.println("🔧 SPRING creando Configuracion...");
    }
}
