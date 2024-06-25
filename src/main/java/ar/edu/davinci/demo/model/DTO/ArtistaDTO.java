package ar.edu.davinci.demo.model.DTO;

import ar.edu.davinci.demo.model.Disco;
import ar.edu.davinci.demo.model.Genero;
import ar.edu.davinci.demo.model.Instrumento;
import ar.edu.davinci.demo.model.Pais;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

public class ArtistaDTO {

    private String nombre;
    private String genero;
    private String paisOrigen;
    private Date fechaNacimiento;
    private Date fechaFallecimiento;
    private List<Instrumento> instrumentos;
    private String biografia;
    private List<Long> discos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
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

    public List<Long> getDiscos() {
        return discos;
    }

    public void setDiscos(List<Long> discos) {
        this.discos = discos;
    }
}
