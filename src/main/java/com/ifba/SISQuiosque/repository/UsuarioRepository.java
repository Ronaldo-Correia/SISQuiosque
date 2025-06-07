package com.ifba.SISQuiosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ifba.SISQuiosque.entity.UsuarioEntity;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    boolean existsUsuarioEntityByEmail(String email);

    Optional<UsuarioEntity> findByEmail(String email);

}
