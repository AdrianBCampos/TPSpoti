package ar.edu.davinci.demo.repository;

import ar.edu.davinci.demo.model.Disco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, Long> {
}
