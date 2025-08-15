package pe.estudio.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.estudio.spring.Pokemon;
import pe.estudio.spring.PokemonServicio;
import pe.estudio.spring.dto.EstadisticasResponse;
import pe.estudio.spring.dto.PokemonRequest;

import java.util.List;

/**
 * REST Controller para gestionar Pokemon
 * Endpoints:
 * - GET /api/pokemon - Obtener todos
 * - GET /api/pokemon/{id} - Obtener por ID
 * - POST /api/pokemon - Crear nuevo
 * - PUT /api/pokemon/{id}/entrenar - Entrenar
 * - DELETE /api/pokemon/{id} - Eliminar
 * - GET /api/pokemon/estadisticas - Estadísticas
 */
@RestController
@RequestMapping("/api/pokemon")
public class PokemonRestController {

    private final PokemonServicio pokemonServicio;

    public PokemonRestController(PokemonServicio pokemonServicio) {
        this.pokemonServicio = pokemonServicio;
        System.out.println("🔥 [SPRING BOOT] PokemonRestController creado");
    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> obtenerTodos() {
        System.out.println("📋 GET /api/pokemon - Obteniendo todos los pokémon");

        List<Pokemon> pokemones = pokemonServicio.obtenerTodos();
        return ResponseEntity.ok(pokemones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> obtenerPorId(@PathVariable int id) {
        System.out.println("🔍 GET /api/pokemon/" + id + " - Buscando pokémon");

        Pokemon pokemon = pokemonServicio.buscarPorId(id);

        if (pokemon != null) {
            return ResponseEntity.ok(pokemon);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Pokemon> crear(@RequestBody PokemonRequest request) {
        System.out.println("⚡ POST /api/pokemon - Creando: " + request.getNombre() + " (" + request.getTipo() + ")");

        try {
            Pokemon nuevo = pokemonServicio.capturarPokemon(request.getNombre(), request.getTipo());
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/entrenar")
    public ResponseEntity<Pokemon> entrenar(@PathVariable int id) {
        System.out.println("💪 PUT /api/pokemon/" + id + "/entrenar - Entrenando pokémon");

        Pokemon pokemon = pokemonServicio.buscarPorId(id);
        if (pokemon == null) {
            return ResponseEntity.notFound().build();
        }

        pokemonServicio.entrenar(id);
        Pokemon pokemonActualizado = pokemonServicio.buscarPorId(id);
        return ResponseEntity.ok(pokemonActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> liberar(@PathVariable int id) {
        System.out.println("🗑️ DELETE /api/pokemon/" + id + " - Liberando pokémon");

        Pokemon pokemon = pokemonServicio.buscarPorId(id);
        if (pokemon == null) {
            return ResponseEntity.notFound().build();
        }

        pokemonServicio.liberarPokemon(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estadisticas")
    public ResponseEntity<EstadisticasResponse> obtenerEstadisticas() {
        System.out.println("📊 GET /api/pokemon/estadisticas - Calculando estadísticas");

        List<Pokemon> pokemones = pokemonServicio.obtenerTodos();

        EstadisticasResponse stats = new EstadisticasResponse();
        stats.setTotalPokemon(pokemones.size());

        if (!pokemones.isEmpty()) {
            double nivelPromedio = pokemones.stream()
                    .mapToInt(Pokemon::getNivel)
                    .average()
                    .orElse(0.0);
            stats.setNivelPromedio(nivelPromedio);

            int nivelMaximo = pokemones.stream()
                    .mapToInt(Pokemon::getNivel)
                    .max()
                    .orElse(0);
            stats.setNivelMaximo(nivelMaximo);
        }

        return ResponseEntity.ok(stats);
    }
}