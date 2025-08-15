package pe.estudio.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.estudio.spring.Pokemon;
import pe.estudio.spring.PokemonServicio;

import java.util.List;

@RestController  // ← Cambiado de @Controller a @RestController
@RequestMapping("/api/pokemon")  // ← Todas las URLs empiezan con /api/pokemon
public class PokemonRestController {

    private final PokemonServicio pokemonServicio;

    public PokemonRestController(PokemonServicio pokemonServicio) {
        this.pokemonServicio = pokemonServicio;
        System.out.println("🔥 Creando PokemonRestController para API REST");
    }

    // GET /api/pokemon - Obtener todos los pokémon
    @GetMapping
    public ResponseEntity<List<Pokemon>> obtenerTodos() {
        System.out.println("📋 GET /api/pokemon - Obteniendo todos los pokémon");

        List<Pokemon> pokemones = pokemonServicio.obtenerTodos();
        return ResponseEntity.ok(pokemones);
        // Spring convierte automáticamente la List<Pokemon> a JSON
    }

    // GET /api/pokemon/{id} - Obtener un pokémon específico
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> obtenerPorId(@PathVariable int id) {
        System.out.println("🔍 GET /api/pokemon/" + id + " - Buscando pokémon");

        Pokemon pokemon = pokemonServicio.buscarPorId(id);

        if (pokemon != null) {
            return ResponseEntity.ok(pokemon);  // 200 OK + JSON del pokémon
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }

    // POST /api/pokemon - Crear nuevo pokémon
    @PostMapping
    public ResponseEntity<Pokemon> crear(@RequestBody PokemonRequest request) {
        System.out.println("⚡ POST /api/pokemon - Creando: " + request.getNombre() + " (" + request.getTipo() + ")");

        try {
            Pokemon nuevo = pokemonServicio.capturarPokemon(request.getNombre(), request.getTipo());
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);  // 201 Created + JSON del pokémon
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();  // 400 Bad Request
        }
    }

    // PUT /api/pokemon/{id}/entrenar - Entrenar un pokémon
    @PutMapping("/{id}/entrenar")
    public ResponseEntity<Pokemon> entrenar(@PathVariable int id) {
        System.out.println("💪 PUT /api/pokemon/" + id + "/entrenar - Entrenando pokémon");

        Pokemon pokemon = pokemonServicio.buscarPorId(id);
        if (pokemon == null) {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }

        pokemonServicio.entrenar(id);
        Pokemon pokemonActualizado = pokemonServicio.buscarPorId(id);
        return ResponseEntity.ok(pokemonActualizado);  // 200 OK + JSON actualizado
    }

    // DELETE /api/pokemon/{id} - Liberar (eliminar) pokémon
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> liberar(@PathVariable int id) {
        System.out.println("🗑️ DELETE /api/pokemon/" + id + " - Liberando pokémon");

        Pokemon pokemon = pokemonServicio.buscarPorId(id);
        if (pokemon == null) {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }

        pokemonServicio.liberarPokemon(id);
        return ResponseEntity.noContent().build();  // 204 No Content (eliminado exitosamente)
    }

    // GET /api/pokemon/estadisticas - Obtener estadísticas
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

// === CLASES DTO (Data Transfer Object) ===

class PokemonRequest {
    private String nombre;
    private String tipo;

    // Constructor vacío para JSON deserialization
    public PokemonRequest() {}

    public PokemonRequest(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}

class EstadisticasResponse {
    private int totalPokemon;
    private double nivelPromedio;
    private int nivelMaximo;

    public EstadisticasResponse() {}

    public int getTotalPokemon() { return totalPokemon; }
    public void setTotalPokemon(int totalPokemon) { this.totalPokemon = totalPokemon; }

    public double getNivelPromedio() { return nivelPromedio; }
    public void setNivelPromedio(double nivelPromedio) { this.nivelPromedio = nivelPromedio; }

    public int getNivelMaximo() { return nivelMaximo; }
    public void setNivelMaximo(int nivelMaximo) { this.nivelMaximo = nivelMaximo; }
}
