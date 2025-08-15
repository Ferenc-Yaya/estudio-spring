package pe.estudio.spring.dto;

/**
 * DTO (Data Transfer Object) para recibir datos al crear un Pokemon
 */
public class PokemonRequest {
    private String nombre;
    private String tipo;

    // Constructor vacío necesario para JSON deserialization
    public PokemonRequest() {}

    public PokemonRequest(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "PokemonRequest{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
