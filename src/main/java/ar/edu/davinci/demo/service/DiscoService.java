package ar.edu.davinci.demo.service;

import ar.edu.davinci.demo.exception.ResourceNotFoundException;
import ar.edu.davinci.demo.model.Artista;
import ar.edu.davinci.demo.model.Cancion;
import ar.edu.davinci.demo.model.Disco;
import ar.edu.davinci.demo.model.DTO.DiscoDTO;
import ar.edu.davinci.demo.model.Genero;
import ar.edu.davinci.demo.repository.ArtistaRepository;
import ar.edu.davinci.demo.repository.CancionRepository;
import ar.edu.davinci.demo.repository.DiscoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscoService {

    @Autowired
    private DiscoRepository discoRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private ArtistaService artistaService;

    @Transactional
    public Disco crearDisco(DiscoDTO discoDTO) {
        try {
            validarCamposObligatorios(discoDTO);



            List<Cancion> canciones = obtenerCancionesPorIds(discoDTO.getCancionesIds());

            Disco disco = new Disco();
            disco.setNombre(discoDTO.getNombre());
            disco.setGenero(Genero.valueOf(discoDTO.getGenero().toUpperCase()));
            disco.setFechaLanzamiento(discoDTO.getFechaLanzamiento());

            disco.setCanciones(canciones);

            return discoRepository.save(disco);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error al crear el disco: " + e.getMessage(), e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al acceder a la base de datos", e);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el disco", e);
        }
    }

    private Artista obtenerArtistaPorId(Long artistaId) {
        return artistaRepository.findById(artistaId)
                .orElseThrow(() -> new ResourceNotFoundException("Artista con ID " + artistaId + " no encontrado."));
    }

    private List<Cancion> obtenerCancionesPorIds(List<Long> cancionesIds) {
        try {
            return cancionRepository.findAllById(cancionesIds);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Alguno de los IDs de canciones no es válido", e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al acceder a la base de datos para obtener las canciones", e);
        }
    }

    private void validarCamposObligatorios(DiscoDTO discoDTO) {
        if (discoDTO.getNombre() == null || discoDTO.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del disco no puede estar vacío.");
        }
        if (discoDTO.getGenero() == null || discoDTO.getGenero().isEmpty()) {
            throw new IllegalArgumentException("El género del disco no puede estar vacío.");
        }
        if (discoDTO.getFechaLanzamiento() == null) {
            throw new IllegalArgumentException("La fecha de lanzamiento del disco no puede estar vacía.");
        }

        if (discoDTO.getCancionesIds() == null || discoDTO.getCancionesIds().isEmpty()) {
            throw new IllegalArgumentException("La lista de IDs de canciones no puede estar vacía.");
        }
    }

    public Optional<Disco> buscarDiscoPorId(Long id) {
        return discoRepository.findById(id);
    }

    public List<Disco> buscarTodosLosDiscos() {
        return discoRepository.findAll();
    }

    public Disco actualizarDisco(Long id, DiscoDTO discoDTO) {
        try {
            Disco disco = discoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Disco con id " + id + " no encontrado."));

            if (discoDTO.getNombre() != null && !discoDTO.getNombre().isEmpty()) {
                disco.setNombre(discoDTO.getNombre());
            }

            if (discoDTO.getGenero() != null && !discoDTO.getGenero().isEmpty()) {
                disco.setGenero(Genero.valueOf(discoDTO.getGenero().toUpperCase()));
            }

            if (discoDTO.getFechaLanzamiento() != null) {
                disco.setFechaLanzamiento(discoDTO.getFechaLanzamiento());
            }



            // Actualizar canciones del disco si se proporcionan nuevas cancionesIds en discoDTO
            if (discoDTO.getCancionesIds() != null && !discoDTO.getCancionesIds().isEmpty()) {
                List<Cancion> canciones = discoDTO.getCancionesIds().stream()
                        .map(cancionId -> new Cancion(cancionId)) // Asumiendo constructor Cancion(Long id)
                        .collect(Collectors.toList());
                disco.setCanciones(canciones);
            }

            return discoRepository.save(disco);
        } catch (IllegalArgumentException e) {
            // Manejo de excepción si alguno de los datos es inválido
            throw new RuntimeException(e.getMessage(), e);
        } catch (DataAccessException e) {
            // Manejo de excepción si hay un error al acceder a la base de datos
            throw new RuntimeException("Error al acceder a la base de datos", e);
        } catch (Exception e) {
            // Manejo de cualquier otra excepción inesperada
            throw new RuntimeException("Error al actualizar el disco", e);
        }
    }





    public void eliminarDisco(Disco disco) {
        discoRepository.delete(disco);
    }
}
