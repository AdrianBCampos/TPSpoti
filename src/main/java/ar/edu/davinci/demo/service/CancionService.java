package ar.edu.davinci.demo.service;

import ar.edu.davinci.demo.model.Cancion;
import ar.edu.davinci.demo.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancionService {
    @Autowired
    private CancionRepository cancionRepository;

    public Cancion crearCancion(Cancion cancion){
        cancionRepository.save(cancion);
        return cancion;
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

    public Cancion actualizarCancion(Cancion cancion) {
        // Aquí puedes realizar alguna validación adicional si lo necesitas

        // Guardar la canción actualizada en la base de datos
        return cancionRepository.save(cancion);
    }
}
