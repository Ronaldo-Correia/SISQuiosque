package com.ifba.SISQuiosque.dto;


import lombok.Data;
import java.util.List;

@Data
public class PedidoDTO {
    private List<ItemPedidoDTO> itens;
    private Double total;
    
    @Data
    public static class ItemPedidoDTO {
        private String nome;
        private Double preco;
    }
}