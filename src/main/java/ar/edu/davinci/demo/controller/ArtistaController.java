package ar.edu.davinci.demo.controller;

import ar.edu.davinci.demo.model.Artista;
import ar.edu.davinci.demo.model.DTO.ArtistaDTO;
import ar.edu.davinci.demo.service.ArtistaService;
import ar.edu.davinci.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @GetMapping
    public List<Artista> buscarTodosLosArtistas() {
        return artistaService.buscarTodosLosArtistas();
    }

    @PostMapping
    public ResponseEntity<Object> crearArtista(@RequestBody ArtistaDTO artistaDTO) {
        try {
            Artista artistaGuardado = artistaService.crearArtista(artistaDTO);
            return ResponseEntity.ok(artistaGuardado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Artista buscarArtistaPorId(@PathVariable Long id) {
        return artistaService.buscarArtistaPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artista con id " + id + " no encontrado."));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> modificarArtista(@PathVariable Long id, @RequestBody ArtistaDTO artistaDTO) {
        try {
            Artista artistaActualizado = artistaService.actualizarArtista(id, artistaDTO);
            return ResponseEntity.ok(artistaActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarArtista(@PathVariable Long id) {
        Artista artista = artistaService.buscarArtistaPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artista no encontrado con id " + id));
        artistaService.eliminarArtista(artista);
        return ResponseEntity.ok("El artista \"" + artista.getNombre() + "\" fue eliminado.");
    }
}
