package ar.edu.davinci.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "canciones")
public class Cancion {
    @Id
    @GeneratedValue
    private long id;
    private String nombre;
    private String letra;
    @Enumerated(EnumType.STRING)
    private Genero genero;

    public Cancion(){

    }

    public Cancion(String nombre, String letra, Genero genero){
        this.nombre = nombre;
        this.letra = letra;
        this.genero = genero;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}
