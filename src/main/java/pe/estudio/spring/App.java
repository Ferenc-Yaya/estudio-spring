package pe.estudio.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(Configuracion.class);
        PokemonServicio pokemonServicio = context.getBean(PokemonServicio.class);
        System.out.println("🌟 ¡Bienvenido al mundo Pokémon! 🌟");

        pokemonServicio.capturarPokemon("Pikachu", "Eléctrico");
        pokemonServicio.capturarPokemon("Charmander", "Fuego");
        pokemonServicio.capturarPokemon("Bulbasaur", "Planta");

        pokemonServicio.mostrarTodosLosPokemons();
        pokemonServicio.entrenar(1); // Entrenar a Pikachu
        pokemonServicio.entrenar(2); // Entrenar a Charmander

        pokemonServicio.mostrarTodosLosPokemons();
        pokemonServicio.mostrarEstadisticas();


        pokemonServicio.liberarPokemon(1); // Liberar a Pikachu

        pokemonServicio.mostrarTodosLosPokemons();
        pokemonServicio.mostrarEstadisticas();

        pokemonServicio.buscarPokemon(2); // Buscar a Charmander
    }
}
