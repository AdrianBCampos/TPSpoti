package ar.edu.davinci.demo.repository;

import ar.edu.davinci.demo.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {
    List<Cancion> findByNombreContainingIgnoreCase(String nombre);
    List<Cancion> findByLetraContainingIgnoreCase(String letra);
}
