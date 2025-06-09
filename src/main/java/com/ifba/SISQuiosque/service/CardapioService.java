package com.ifba.SISQuiosque.service;

import com.ifba.SISQuiosque.dto.CardapioItemDTO;
import java.util.List;

public interface CardapioService {
    List<CardapioItemDTO> listarItensAtivos();
    CardapioItemDTO buscarPorId(Long id);
}