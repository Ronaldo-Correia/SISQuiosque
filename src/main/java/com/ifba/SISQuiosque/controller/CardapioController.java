package com.ifba.SISQuiosque.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CardapioController {

    @GetMapping("/cardapio")
    public String mostrarCardapio() {
        return "cardapio"; 
    }
}