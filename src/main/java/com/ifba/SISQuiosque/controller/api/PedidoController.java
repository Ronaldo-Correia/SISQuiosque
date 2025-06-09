package com.ifba.SISQuiosque.controller.api;

import com.ifba.SISQuiosque.dto.PedidoDTO;
import com.ifba.SISQuiosque.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public String finalizarPedido(@RequestBody PedidoDTO pedidoDTO) {
        pedidoService.salvarPedido(pedidoDTO);
        return "Pedido realizado com sucesso!";
    }
}