package com.ifba.SISQuiosque.controller;

import com.ifba.SISQuiosque.dto.PedidoDTO;
import com.ifba.SISQuiosque.entity.Pedido;
import com.ifba.SISQuiosque.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // Endpoint para receber e finalizar o pedido (salvar no banco)
    @PostMapping
    public ResponseEntity<?> finalizarPedido(@RequestBody PedidoDTO pedidoDTO) {

        // Validações básicas
        if (pedidoDTO.getItens() == null || pedidoDTO.getItens().isEmpty()) {
            return ResponseEntity.badRequest().body("O pedido deve conter pelo menos um item.");
        }

        if (pedidoDTO.getTotal() == null || pedidoDTO.getTotal().compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.badRequest().body("Total inválido.");
        }

        if (pedidoDTO.getFormaPagamento() == null || pedidoDTO.getFormaPagamento().isBlank()) {
            return ResponseEntity.badRequest().body("Forma de pagamento é obrigatória.");
        }

        if ("DINHEIRO".equalsIgnoreCase(pedidoDTO.getFormaPagamento())) {
            if (pedidoDTO.getValorPago() == null || pedidoDTO.getValorPago().compareTo(pedidoDTO.getTotal()) < 0) {
                return ResponseEntity.badRequest().body("Valor pago deve ser maior ou igual ao total.");
            }
        }

        // Salva o pedido
        Pedido pedidoSalvo = pedidoService.salvarPedido(pedidoDTO);

        // Retorna resposta com id e troco
        return ResponseEntity.ok(
            new Object() {
                public final String mensagem = "Pedido finalizado com sucesso!";
                public final String idPedido = pedidoSalvo.getId().toString();
                public final String troco = pedidoSalvo.getTroco().toString();
            }
        );
    }
}
