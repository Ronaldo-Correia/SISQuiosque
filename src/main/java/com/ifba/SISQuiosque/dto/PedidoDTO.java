package com.ifba.SISQuiosque.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO {

    public static class ItemDTO {
        private String nome;
        private BigDecimal preco;

        // getters e setters
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
        public BigDecimal getPreco() { return preco; }
        public void setPreco(BigDecimal preco) { this.preco = preco; }
    }

    private List<ItemDTO> itens;
    private BigDecimal total;
    private String formaPagamento;  // DINHEIRO, PIX, CARTAO
    private BigDecimal valorPago;   // Pode ser null se não for dinheiro

    // getters e setters
    public List<ItemDTO> getItens() { return itens; }
    public void setItens(List<ItemDTO> itens) { this.itens = itens; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }
    public BigDecimal getValorPago() { return valorPago; }
    public void setValorPago(BigDecimal valorPago) { this.valorPago = valorPago; }
}
