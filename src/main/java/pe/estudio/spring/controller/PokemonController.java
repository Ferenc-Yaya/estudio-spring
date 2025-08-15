package pe.estudio.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.estudio.spring.Pokemon;
import pe.estudio.spring.PokemonServicio;

@Controller  // ← Esto marca la clase como Controller de Spring MVC
public class PokemonController {

    private final PokemonServicio pokemonServicio;

    // ✅ BUENA PRÁCTICA: Inyección por constructor
    public PokemonController(PokemonServicio pokemonServicio) {
        this.pokemonServicio = pokemonServicio;
        System.out.println("🎮 Creando PokemonController con servicio inyectado");
    }

    // Mostrar la página principal
    @GetMapping("/")  // ← http://localhost:8080/pokemon/
    public String inicio() {
        System.out.println("🏠 GET / - Mostrando página principal");

        return "pokemon-inicio";  // → /WEB-INF/views/pokemon-inicio.html
    }

    // Procesar el formulario de captura
    @PostMapping("/capturar")  // ← cuando se envía el formulario
    public String capturarPokemon(@RequestParam String nombre,  // del input name="nombre"
                                  @RequestParam String tipo) {  // del input name="tipo"

        System.out.println("⚡ POST /capturar - Recibido: " + nombre + " (" + tipo + ")");

        try {
            Pokemon nuevo = pokemonServicio.capturarPokemon(nombre, tipo);
            System.out.println("✅ Pokémon capturado: " + nuevo.getNombre());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        // Regresar a la página principal
        return "redirect:/";  // → GET /
    }
}