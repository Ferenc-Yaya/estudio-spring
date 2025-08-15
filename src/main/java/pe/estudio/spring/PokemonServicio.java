package pe.estudio.spring;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonServicio {
    private PokemonRepositorio pokemonRepositorio;

    public PokemonServicio(PokemonRepositorio pokemonRepositorio) {
        this.pokemonRepositorio = pokemonRepositorio;
        System.out.println("🔧 MANUALMENTE creando PokemonServicio...");
    }

    public Pokemon capturarPokemon(String nombre, String tipo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("¡No puedes capturar un Pokémon sin nombre!");
        }
        return pokemonRepositorio.capturarPokemon(nombre, tipo);
    }

    public void buscarPokemon(int id) {
        Pokemon pokemon = pokemonRepositorio.buscarPokemon(id);
        System.out.println(pokemon);
    }

    public void entrenar(int id) {
        Pokemon pokemon = pokemonRepositorio.buscarPokemon(id);
        if (pokemon == null) {
            System.out.println("❌ No se encontró ese Pokémon");
            return;
        }

        System.out.println("💪 Entrenando a " + pokemon.getNombre() + "...");
        int expGanada = (int)(Math.random() * 50) + 10; // Entre 10 y 59 EXP
        pokemonRepositorio.entrenar(id, expGanada);
        System.out.println("✨ " + pokemon.getNombre() + " ganó " + expGanada + " puntos de experiencia!");
    }

    public void liberarPokemon(int id) {
        pokemonRepositorio.liberarPokemon(id);
    }

    public void mostrarTodosLosPokemons(){
        List<Pokemon> pokemons = pokemonRepositorio.obtenerTodos();
        if (pokemons.isEmpty()) {
            System.out.println("No tienes Pokémons capturados.");
        } else {
            System.out.println("Tus Pokémons capturados:");
            pokemons.forEach(System.out::println);
        }
    }

    public void mostrarEstadisticas(){
        List<Pokemon> pokemones = pokemonRepositorio.obtenerTodos();
        int totalPokemon = pokemones.size();
        int nivelPromedio = pokemones.stream().mapToInt(Pokemon::getNivel).sum() / Math.max(totalPokemon, 1);

        System.out.println("\n📊 === ESTADÍSTICAS ===");
        System.out.println("Pokémon capturados: " + totalPokemon);
        System.out.println("Nivel promedio: " + nivelPromedio);
        System.out.println("======================\n");
    }

    public Pokemon buscarPorId(int id) {
        return pokemonRepositorio.buscarPokemon(id);
    }

    public List<Pokemon> obtenerTodos() {
        return pokemonRepositorio.obtenerTodos();
    }

}
