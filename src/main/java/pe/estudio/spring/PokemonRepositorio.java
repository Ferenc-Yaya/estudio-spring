package pe.estudio.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonRepositorio {
    private Map<Integer, Pokemon> pokemones = new HashMap<>();
    private int contadorId=1;

    public PokemonRepositorio() {
        System.out.println("🔧 MANUALMENTE creando PokemonRepositorio...");
    }

    public Pokemon capturarPokemon(String nombre,String tipo){
        Pokemon pokemon=new Pokemon(this.contadorId++, nombre, tipo);
        pokemones.put(pokemon.getId(), pokemon);
        System.out.println("⚡ ¡Capturaste a " + pokemon.getNombre() + "!");
        return pokemon;
    }

    public Pokemon buscarPokemon(int id) {
        return pokemones.get(id);
    }

    public List<Pokemon> obtenerTodos(){
        return new ArrayList<>(pokemones.values());
    }

    public void entrenar(int id,int experiencia){
        Pokemon pokemon= buscarPokemon(id);
        if (pokemon != null) {
            pokemon.ganarExperiencia(experiencia);
        }
    }

    public void liberarPokemon(int id) {
        if (pokemones.containsKey(id)) {
            pokemones.remove(id);
            System.out.println("🗑️ ¡Has liberado al Pokémon con ID " + id + "!");
        } else {
            System.out.println("❌ No se encontró un Pokémon con ID " + id + ".");
        }
    }
}
