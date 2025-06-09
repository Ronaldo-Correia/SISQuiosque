package com.ifba.SISQuiosque.controller;

import com.ifba.SISQuiosque.dto.ItemPedidoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PedidoViewController {

    @GetMapping("/finalizar-pedido")
    public String finalizarPedido(HttpSession session, Model model) {
        List<ItemPedidoDTO> itensCarrinho = getCarrinhoDaSessao(session);
        
        if (carrinhoVazio(itensCarrinho)) {
            return "redirect:/cardapio?carrinhoVazio=true";
        }
        
        model.addAttribute("itensCarrinho", itensCarrinho);
        model.addAttribute("totalPedido", calcularTotal(itensCarrinho));
        
        // Certifique-se que corresponde ao nome do arquivo JTE
        return "finalizar-pedido"; 
    }

    // Métodos auxiliares
    private List<ItemPedidoDTO> getCarrinhoDaSessao(HttpSession session) {
        return (List<ItemPedidoDTO>) session.getAttribute("carrinho");
    }

    private boolean carrinhoVazio(List<ItemPedidoDTO> itens) {
        return itens == null || itens.isEmpty();
    }

    private double calcularTotal(List<ItemPedidoDTO> itens) {
        return itens.stream()
                  .mapToDouble(ItemPedidoDTO::getPreco)
                  .sum();
    }
}