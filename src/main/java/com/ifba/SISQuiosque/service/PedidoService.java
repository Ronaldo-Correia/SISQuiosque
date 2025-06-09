package com.ifba.SISQuiosque.service;

import com.ifba.SISQuiosque.dto.PedidoDTO;
import org.springframework.stereotype.Service;
@Service
public interface PedidoService {
    void salvarPedido(PedidoDTO pedidoDTO);
}