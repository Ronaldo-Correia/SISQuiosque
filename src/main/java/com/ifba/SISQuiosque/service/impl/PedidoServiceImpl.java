package com.ifba.SISQuiosque.service.impl;

import com.ifba.SISQuiosque.dto.PedidoDTO;
import com.ifba.SISQuiosque.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Override
    public void salvarPedido(PedidoDTO pedidoDTO) {
        // Implementação para salvar o pedido
        System.out.println("Pedido recebido: " + pedidoDTO);
    }
}