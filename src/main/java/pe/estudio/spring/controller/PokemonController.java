package pe.estudio.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.estudio.spring.Pokemon;
import pe.estudio.spring.PokemonServicio;

@Controller
public class PokemonController {

    private final PokemonServicio pokemonServicio;

    public PokemonController(PokemonServicio pokemonServicio) {
        this.pokemonServicio = pokemonServicio;
        System.out.println("🎮 Creando PokemonController con servicio inyectado");
    }

    @GetMapping(value = "/", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String inicio() {
        System.out.println("🏠 GET / - Mostrando página principal");

        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Pokemon MVC</title>
                <meta charset="UTF-8">
            </head>
            <body>
                <h1>¡FUNCIONA! Spring MVC</h1>
                
                <p><strong>¡Éxito!</strong> Spring MVC está funcionando correctamente.</p>
                
                <h2>Capturar Pokemon</h2>
                <form action="/pokemon/capturar" method="POST">
                    <p>
                        <label>Nombre:</label><br>
                        <input type="text" name="nombre" placeholder="Pikachu" required>
                    </p>
                    <p>
                        <label>Tipo:</label><br>
                        <input type="text" name="tipo" placeholder="Electrico" required>
                    </p>
                    <p>
                        <button type="submit">Capturar</button>
                    </p>
                </form>
                
                <div style="background: #e3f2fd; padding: 10px; margin: 10px 0;">
                    <strong>¡Tu primera aplicación Spring MVC funciona!</strong><br>
                    HTML generado directamente desde Java.
                </div>
                
            </body>
            </html>
            """;
    }

    @PostMapping(value = "/capturar", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String capturarPokemon(@RequestParam String nombre,
                                  @RequestParam String tipo) {

        System.out.println("⚡ POST /capturar - Recibido: " + nombre + " (" + tipo + ")");

        try {
            Pokemon nuevo = pokemonServicio.capturarPokemon(nombre, tipo);
            System.out.println("✅ Pokémon capturado: " + nuevo.getNombre());

            return "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<title>Pokemon Capturado</title>" +
                    "<meta charset=\"UTF-8\">" +
                    "</head>" +
                    "<body>" +
                    "<h1>¡Pokemon Capturado!</h1>" +
                    "<p><strong>Nombre:</strong> " + nuevo.getNombre() + "</p>" +
                    "<p><strong>Tipo:</strong> " + nuevo.getTipo() + "</p>" +
                    "<p><strong>ID:</strong> " + nuevo.getId() + "</p>" +
                    "<a href=\"/pokemon/\">Volver</a>" +
                    "</body>" +
                    "</html>";

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            return "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<title>Error</title>" +
                    "<meta charset=\"UTF-8\">" +
                    "</head>" +
                    "<body>" +
                    "<h1>Error</h1>" +
                    "<p>" + e.getMessage() + "</p>" +
                    "<a href=\"/pokemon/\">Volver</a>" +
                    "</body>" +
                    "</html>";
        }
    }
}