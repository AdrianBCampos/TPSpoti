package ar.edu.davinci.demo.service;

import ar.edu.davinci.demo.exception.ResourceNotFoundException;
import ar.edu.davinci.demo.model.Cancion;
import ar.edu.davinci.demo.model.DTO.CancionDTO;
import ar.edu.davinci.demo.model.Genero;
import ar.edu.davinci.demo.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CancionService {
    @Autowired
    private CancionRepository cancionRepository;


    public Cancion crearCancion(CancionDTO cancionDTO) {
        try {
            // Validar que los campos no estén vacíos
            if (cancionDTO.getNombre() == null || cancionDTO.getNombre().isEmpty()) {
                throw new IllegalArgumentException("El nombre de la canción no puede estar vacío.");
            }
            if (cancionDTO.getLetra() == null || cancionDTO.getLetra().isEmpty()) {
                throw new IllegalArgumentException("La letra de la canción no puede estar vacía.");
            }
            if (cancionDTO.getGenero() == null || cancionDTO.getGenero().isEmpty()) {
                throw new IllegalArgumentException("El género de la canción no puede estar vacío.");
            }

            // Crear la canción
            Cancion cancion = new Cancion();
            cancion.setNombre(cancionDTO.getNombre());
            cancion.setLetra(cancionDTO.getLetra());
            cancion.setGenero(Genero.valueOf(cancionDTO.getGenero().toUpperCase()));

            // Guardar la canción en la base de datos
            return cancionRepository.save(cancion);
        } catch (IllegalArgumentException e) {
            // Manejo de excepción si alguno de los datos está vacío
            throw new IllegalArgumentException(e.getMessage(), e);
        } catch (DataAccessException e) {
            // Manejo de excepción si hay un error al acceder a la base de datos
            throw new RuntimeException("Error al acceder a la base de datos", e);
        } catch (Exception e) {
            // Manejo de cualquier otra excepción inesperada
            throw new RuntimeException("Error al crear la canción", e);
        }
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
        try {
            Cancion cancion = cancionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Cancion con id " + id + " no encontrada."));

            if (cancionDTO.getNombre() == null || cancionDTO.getNombre().isEmpty()) {
                throw new IllegalArgumentException("El nombre de la canción no puede estar vacío.");
            }
            if (cancionDTO.getLetra() == null || cancionDTO.getLetra().isEmpty()) {
                throw new IllegalArgumentException("La letra de la canción no puede estar vacía.");
            }
            if (cancionDTO.getGenero() == null || cancionDTO.getGenero().isEmpty()) {
                throw new IllegalArgumentException("El género de la canción no puede estar vacío.");
            }

            cancion.setNombre(cancionDTO.getNombre());
            cancion.setLetra(cancionDTO.getLetra());
            cancion.setGenero(Genero.valueOf(cancionDTO.getGenero().toUpperCase()));

            return cancionRepository.save(cancion);
        } catch (IllegalArgumentException e) {
            // Manejo de excepción si algún campo es inválido
            throw new RuntimeException(e.getMessage(), e);
        } catch (DataAccessException e) {
            // Manejo de excepción si hay un error al acceder a la base de datos
            throw new RuntimeException("Error al acceder a la base de datos", e);
        } catch (Exception e) {
            // Manejo de cualquier otra excepción inesperada
            throw new RuntimeException("Error al actualizar la canción", e);
        }
    }
}
