package com.ifba.SISQuiosque.controller.api;

import com.ifba.SISQuiosque.dto.ItemPedidoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoApiController {

    @PostMapping("/salvar")
    public ResponseEntity<?> salvarCarrinho(@RequestBody List<ItemPedidoDTO> itens, 
                                          HttpSession session) {
        try {
            validarItens(itens);
            session.setAttribute("carrinho", itens);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ItemPedidoDTO>> obterCarrinho(HttpSession session) {
        List<ItemPedidoDTO> carrinho = (List<ItemPedidoDTO>) session.getAttribute("carrinho");
        return ResponseEntity.ok(carrinho);
    }

    @DeleteMapping
    public ResponseEntity<Void> limparCarrinho(HttpSession session) {
        session.removeAttribute("carrinho");
        return ResponseEntity.noContent().build();
    }

    private void validarItens(List<ItemPedidoDTO> itens) {
        if (itens == null) {
            throw new IllegalArgumentException("Lista de itens não pode ser nula");
        }
        
        itens.forEach(item -> {
            if (item == null) {
                throw new IllegalArgumentException("Item do carrinho não pode ser nulo");
            }
            if (item.getNome() == null || item.getNome().trim().isEmpty()) {
                throw new IllegalArgumentException("Nome do item não pode ser vazio");
            }
            if (item.getPreco() <= 0) {
                throw new IllegalArgumentException("Preço deve ser maior que zero");
            }
        });
    }
}