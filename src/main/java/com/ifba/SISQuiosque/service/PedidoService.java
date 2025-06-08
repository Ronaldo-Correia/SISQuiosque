package com.ifba.SISQuiosque.service;

import com.ifba.SISQuiosque.dto.PedidoDTO;
import com.ifba.SISQuiosque.entity.ItemPedido;
import com.ifba.SISQuiosque.entity.Pedido;
import com.ifba.SISQuiosque.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido salvarPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();

        pedido.setFormaPagamento(pedidoDTO.getFormaPagamento());
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setValorPago(pedidoDTO.getValorPago());

        // Calcular troco se for pagamento em dinheiro e valorPago informado
        if ("DINHEIRO".equalsIgnoreCase(pedidoDTO.getFormaPagamento()) && pedidoDTO.getValorPago() != null) {
            BigDecimal troco = pedidoDTO.getValorPago().subtract(pedidoDTO.getTotal());
            pedido.setTroco(troco.compareTo(BigDecimal.ZERO) > 0 ? troco : BigDecimal.ZERO);
        } else {
            pedido.setTroco(BigDecimal.ZERO);
        }

        // Converter itens DTO para entidade ItemPedido
        List<ItemPedido> itens = pedidoDTO.getItens().stream().map(itemDTO -> {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setNome(itemDTO.getNome());
            itemPedido.setPreco(itemDTO.getPreco());
            return itemPedido;
        }).toList();

        pedido.setItens(itens);

        return pedidoRepository.save(pedido);
    }

}
