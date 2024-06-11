package ar.edu.davinci.demo.controller;

import ar.edu.davinci.demo.model.Cancion;
import ar.edu.davinci.demo.model.Genero;
import ar.edu.davinci.demo.service.CancionService;
import ar.edu.davinci.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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
    public Cancion crearCancion(@RequestBody Map<String, Object> requestBody) {
        String nombre = requestBody.get("nombre").toString();
        String letra = requestBody.get("letra").toString();
        Genero genero = Genero.valueOf(requestBody.get("genero").toString());
        Cancion cancion = new Cancion (nombre, letra, genero);
        return cancionService.crearCancion(cancion);
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
    public Cancion modificarCancion(@PathVariable Long id, @RequestBody Cancion cancionDetails) {
        Cancion cancion = cancionService.buscarCancionPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cancion con id " + id + " ,no encontrada."));

        // Verificar y actualizar el nombre si se proporciona en la solicitud
        if (cancionDetails.getNombre() != null) {
            cancion.setNombre(cancionDetails.getNombre());
        }

        // Verificar y actualizar la letra si se proporciona en la solicitud
        if (cancionDetails.getLetra() != null) {
            cancion.setLetra(cancionDetails.getLetra());
        }

        // Verificar y actualizar el género si se proporciona en la solicitud
        if (cancionDetails.getGenero() != null) {
            cancion.setGenero(cancionDetails.getGenero());
        }

        // Guardar la canción actualizada en la base de datos
        return cancionService.crearCancion(cancion);
    }

    @DeleteMapping("/{id}")
    public void eliminarCancion(@PathVariable Long id) {
        Cancion cancion = cancionService.buscarCancionPorId(id).orElseThrow(() -> new ResourceNotFoundException("Cancion not found with id " + id));
        cancionService.eliminarCancion(cancion);
    }


}
