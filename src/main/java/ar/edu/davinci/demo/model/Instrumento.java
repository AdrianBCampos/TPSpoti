package ar.edu.davinci.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "instrumentos")
public class Instrumento {

    @Id
    @GeneratedValue //(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    public Instrumento() {

    }
    public Instrumento(String nombre) {

        this.nombre = nombre;
    }
}
