package ar.edu.davinci.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue //(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Enumerated(EnumType.STRING)
    private Pais paisOrigen;

    private Date fechaNacimiento;
    private Date fechaFallecimiento;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Instrumento> instrumentos;

    @Lob
    private String biografia;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Disco> discos = new ArrayList<>();

    public Artista (){

    }

    public Artista(String nombre, Genero genero, Pais paisOrigen, Date fechaNacimiento, Date fechaFallecimiento, List<Instrumento> instrumentos, String biografia, List<Disco> discos) {
        this.nombre = nombre;
        this.genero = genero;
        this.paisOrigen = paisOrigen;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
        this.instrumentos = instrumentos;
        this.biografia = biografia;
        this.discos = discos;
    }

    public Disco crearDisco(String nombreDisco, Genero generoDisco, Date fechaLanzamiento, List<Cancion> canciones) {
        Disco disco = new Disco(nombreDisco, generoDisco, fechaLanzamiento, canciones);
        if (discos == null) {
            discos = new ArrayList<>();
        }
        discos.add(disco);  // Agrega el disco a la lista de discos del artista
        return disco;
    }

    public void addDisco(Disco disco) {
        discos.add(disco);
        disco.setArtista(this);
    }

    public void removeDisco(Disco disco) {
        discos.remove(disco);
        disco.setArtista(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Pais getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(Pais paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Date fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Instrumento> getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(List<Instrumento> instrumentos) {
        this.instrumentos = instrumentos;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public List<Disco> getDiscos() {
        return discos;
    }

    public void setDiscos(List<Disco> discos) {
        this.discos = discos;
    }
}
