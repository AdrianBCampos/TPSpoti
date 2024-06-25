package ar.edu.davinci.demo.service;

import ar.edu.davinci.demo.exception.ResourceNotFoundException;
import ar.edu.davinci.demo.model.Artista;
import ar.edu.davinci.demo.model.DTO.ArtistaDTO;
import ar.edu.davinci.demo.model.Disco;
import ar.edu.davinci.demo.model.Genero;
import ar.edu.davinci.demo.model.Pais;
import ar.edu.davinci.demo.repository.ArtistaRepository;
import ar.edu.davinci.demo.repository.DiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {
    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private DiscoRepository discoRepository;

    public Artista crearArtista(ArtistaDTO artistaDTO) {
        try {
            // Verificación de campos obligatorios
            if (artistaDTO.getNombre() == null || artistaDTO.getNombre().isEmpty()) {
                throw new IllegalArgumentException("El nombre del artista no puede estar vacío.");
            }
            if (artistaDTO.getGenero() == null || artistaDTO.getGenero().isEmpty()) {
                throw new IllegalArgumentException("El género del artista no puede estar vacío.");
            }
            if (artistaDTO.getPaisOrigen() == null || artistaDTO.getPaisOrigen().isEmpty()) {
                throw new IllegalArgumentException("El país de origen del artista no puede estar vacío.");
            }

            // Crear el artista
            Artista artista = new Artista();
            artista.setNombre(artistaDTO.getNombre());
            artista.setGenero(Genero.valueOf(artistaDTO.getGenero().toUpperCase()));
            artista.setPaisOrigen(Pais.valueOf(artistaDTO.getPaisOrigen().toUpperCase()));
            artista.setFechaNacimiento(artistaDTO.getFechaNacimiento());
            artista.setFechaFallecimiento(artistaDTO.getFechaFallecimiento());
            artista.setBiografia(artistaDTO.getBiografia());
            artista.setInstrumentos(artistaDTO.getInstrumentos());

            // Asignar discos
            if (artistaDTO.getDiscos() != null) {
                List<Disco> discos = discoRepository.findAllById(artistaDTO.getDiscos());
                for (Disco disco : discos) {
                    artista.addDisco(disco);
                }
            }

            // Guardar el artista en la base de datos
            return artistaRepository.save(artista);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al acceder a la base de datos", e);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el artista", e);
        }
    }

    public Artista actualizarArtista(Long id, ArtistaDTO artistaDTO) {
        try {
            Artista artista = artistaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Artista con id " + id + " no encontrado."));

            if (artistaDTO.getNombre() != null && !artistaDTO.getNombre().isEmpty()) {
                artista.setNombre(artistaDTO.getNombre());
            }

            if (artistaDTO.getGenero() != null && !artistaDTO.getGenero().isEmpty()) {
                artista.setGenero(Genero.valueOf(artistaDTO.getGenero().toUpperCase()));
            }

            if (artistaDTO.getPaisOrigen() != null && !artistaDTO.getPaisOrigen().isEmpty()) {
                artista.setPaisOrigen(Pais.valueOf(artistaDTO.getPaisOrigen().toUpperCase()));
            }

            if (artistaDTO.getFechaNacimiento() != null) {
                artista.setFechaNacimiento(artistaDTO.getFechaNacimiento());
            }

            if (artistaDTO.getFechaFallecimiento() != null) {
                artista.setFechaFallecimiento(artistaDTO.getFechaFallecimiento());
            }

            if (artistaDTO.getInstrumentos() != null && !artistaDTO.getInstrumentos().isEmpty()) {
                artista.setInstrumentos(artistaDTO.getInstrumentos());
            }

            if (artistaDTO.getBiografia() != null && !artistaDTO.getBiografia().isEmpty()) {
                artista.setBiografia(artistaDTO.getBiografia());
            }

            if (artistaDTO.getDiscos() != null && !artistaDTO.getDiscos().isEmpty()) {
                List<Disco> discos = discoRepository.findAllById(artistaDTO.getDiscos());
                artista.getDiscos().clear(); // Opcional: eliminar discos actuales
                for (Disco disco : discos) {
                    artista.addDisco(disco);
                }
            }

            return artistaRepository.save(artista);
        } catch (IllegalArgumentException e) {
            // Manejo de excepción si algún valor es inválido
            throw new RuntimeException(e.getMessage(), e);
        } catch (DataAccessException e) {
            // Manejo de excepción si hay un error al acceder a la base de datos
            throw new RuntimeException("Error al acceder a la base de datos", e);
        } catch (Exception e) {
            // Manejo de cualquier otra excepción inesperada
            throw new RuntimeException("Error al actualizar el artista", e);
        }
    }


    public Optional<Artista> buscarArtistaPorId(Long id) {
        return artistaRepository.findById(id);
    }

    public List<Artista> buscarTodosLosArtistas() {
        return artistaRepository.findAll();
    }

    public void eliminarArtista(Artista artista) {
        artistaRepository.delete(artista);
    }

}
