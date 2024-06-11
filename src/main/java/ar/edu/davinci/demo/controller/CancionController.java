package ar.edu.davinci.demo.controller;

import ar.edu.davinci.demo.model.Cancion;
import ar.edu.davinci.demo.model.DTO.CancionDTO;

import ar.edu.davinci.demo.service.CancionService;
import ar.edu.davinci.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/canciones")
public class CancionController {

    @Autowired
    private CancionService cancionService;

    @GetMapping
    public List<Cancion> buscarTodasLasCanciones() {
        return cancionService.buscarTodasLasCanciones();
    }


    @PostMapping
    public ResponseEntity<Object> crearCancion(@RequestBody CancionDTO cancionDTO) {
        try {
            Cancion cancionGuardada = cancionService.crearCancion(cancionDTO);
            return ResponseEntity.ok(cancionGuardada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Cancion buscarCancionPorId(@PathVariable Long id) {
        return cancionService.buscarCancionPorId(id).orElseThrow(() -> new ResourceNotFoundException("Cancion con id " + id + " ,no encontrada."));
    }

    @GetMapping("/buscarPorNombre")
    public ResponseEntity<List<Cancion>> buscarCancionesPorNombre(@RequestParam String nombre) {
        List<Cancion> canciones = cancionService.buscarCancionesPorNombre(nombre);
        return ResponseEntity.ok(canciones);
    }

    @GetMapping("/buscarPorLetra")
    public ResponseEntity<List<Cancion>> buscarCancionesPorLetra(@RequestParam String letra) {
        List<Cancion> canciones = cancionService.buscarCancionesPorLetra(letra);
        return ResponseEntity.ok(canciones);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cancion> modificarCancion(@PathVariable Long id, @RequestBody CancionDTO cancionDTO) {
        Cancion cancionActualizada = cancionService.actualizarCancion(id, cancionDTO);
        return ResponseEntity.ok(cancionActualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminarCancion(@PathVariable Long id) {
        Cancion cancion = cancionService.buscarCancionPorId(id).orElseThrow(() -> new ResourceNotFoundException("Cancion not found with id " + id));
        cancionService.eliminarCancion(cancion);
    }


}
