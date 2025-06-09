package com.ifba.SISQuiosque.controller.api;

import com.ifba.SISQuiosque.dto.CardapioItemDTO;
import com.ifba.SISQuiosque.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cardapio")
public class CardapioApiController {

    @Autowired
    private CardapioService cardapioService;

    @GetMapping
    public List<CardapioItemDTO> listarCardapio() {
        return cardapioService.listarItensAtivos();
    }

    @GetMapping("/{id}")
    public CardapioItemDTO buscarItem(@PathVariable Long id) {
        return cardapioService.buscarPorId(id);
    }
}