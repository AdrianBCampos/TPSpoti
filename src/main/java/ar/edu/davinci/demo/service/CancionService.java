package ar.edu.davinci.demo.service;

import ar.edu.davinci.demo.exception.ResourceNotFoundException;
import ar.edu.davinci.demo.model.Cancion;
import ar.edu.davinci.demo.model.DTO.CancionDTO;
import ar.edu.davinci.demo.model.Genero;
import ar.edu.davinci.demo.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancionService {
    @Autowired
    private CancionRepository cancionRepository;

    public Cancion crearCancion(CancionDTO cancionDTO){

        Cancion cancion = new Cancion();
        cancion.setNombre(cancionDTO.getNombre());
        cancion.setLetra(cancionDTO.getLetra());
        cancion.setGenero(Genero.valueOf(cancionDTO.getGenero().toUpperCase()));

        return  cancionRepository.save(cancion);
    }


    public Optional<Cancion> buscarCancionPorId(Long id) {
        return cancionRepository.findById(id);
    }

    public List<Cancion> buscarCancionesPorNombre(String nombre) {
        return cancionRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Cancion> buscarCancionesPorLetra(String letra) {
        return cancionRepository.findByLetraContainingIgnoreCase(letra);
    }

    public List<Cancion> buscarTodasLasCanciones(){
        return cancionRepository.findAll();
    }

    public void eliminarCancion(Cancion cancion){
        cancionRepository.delete((cancion));
    }


    public Cancion actualizarCancion(Long id, CancionDTO cancionDTO) {
        Cancion cancion = cancionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cancion con id " + id + " no encontrada."));

        if (cancionDTO.getNombre() != null) {
            cancion.setNombre(cancionDTO.getNombre());
        }

        if (cancionDTO.getLetra() != null) {
            cancion.setLetra(cancionDTO.getLetra());
        }

        if (cancionDTO.getGenero() != null) {
            cancion.setGenero(Genero.valueOf(cancionDTO.getGenero().toUpperCase()));
        }

        return cancionRepository.save(cancion);
    }
}
