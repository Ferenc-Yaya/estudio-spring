package pe.estudio.spring.dto;

/**
 * DTO (Data Transfer Object) para enviar estadísticas de Pokemon
 */
public class EstadisticasResponse {
    private int totalPokemon;
    private double nivelPromedio;
    private int nivelMaximo;

    public EstadisticasResponse() {}

    public EstadisticasResponse(int totalPokemon, double nivelPromedio, int nivelMaximo) {
        this.totalPokemon = totalPokemon;
        this.nivelPromedio = nivelPromedio;
        this.nivelMaximo = nivelMaximo;
    }

    public int getTotalPokemon() {
        return totalPokemon;
    }

    public void setTotalPokemon(int totalPokemon) {
        this.totalPokemon = totalPokemon;
    }

    public double getNivelPromedio() {
        return nivelPromedio;
    }

    public void setNivelPromedio(double nivelPromedio) {
        this.nivelPromedio = nivelPromedio;
    }

    public int getNivelMaximo() {
        return nivelMaximo;
    }

    public void setNivelMaximo(int nivelMaximo) {
        this.nivelMaximo = nivelMaximo;
    }

    @Override
    public String toString() {
        return "EstadisticasResponse{" +
                "totalPokemon=" + totalPokemon +
                ", nivelPromedio=" + nivelPromedio +
                ", nivelMaximo=" + nivelMaximo +
                '}';
    }
}
