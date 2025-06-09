package com.ifba.SISQuiosque.dto;
public record RespostaPedidoDTO(
    boolean sucesso,
    String mensagem,
    Long numeroPedido
) {}
