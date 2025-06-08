package com.ifba.SISQuiosque.repository;

import com.ifba.SISQuiosque.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Aqui pode adicionar consultas específicas se quiser
}
