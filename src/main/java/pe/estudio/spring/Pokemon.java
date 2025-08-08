package pe.estudio.spring;

public class Pokemon {
    private int id;
    private String nombre;
    private String tipo;
    private int nivel;
    private int experiencia;

    public Pokemon(int id, String nombre, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = 1;
        this.experiencia = 0;
    }

    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public String getTipo() {return tipo;}
    public int getNivel() {return nivel;}
    public int getExperiencia() {return experiencia;}

    public void ganarExperiencia(int puntos) {
        this.experiencia += puntos;
        if (this.experiencia >= 100) {
            this.nivel++;
            this.experiencia = 0;
            System.out.println(nombre + " ha subido de nivel a " + nivel + "!");
        }
    }

    @Override
    public String toString() {
        return id + ". " + nombre + " (" + tipo + ") - Nivel " + nivel + " [" + experiencia + "/100 EXP]";
    }


}
