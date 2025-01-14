package com.alura.forohub.repository;

import com.alura.forohub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Boolean existsByTituloAndMensaje(String titulo, String mensaje);
}
