package com.ifba.SISQuiosque.service.impl;
import com.ifba.SISQuiosque.dto.CardapioItemDTO;
import com.ifba.SISQuiosque.entity.CardapioItem;
import com.ifba.SISQuiosque.mapping.CardapioItemMapper;
import com.ifba.SISQuiosque.repository.CardapioItemRepository;
import com.ifba.SISQuiosque.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardapioServiceImpl implements CardapioService {

    @Autowired
    private CardapioItemRepository repository;
    
    @Autowired
    private CardapioItemMapper mapper;

    @Override
    public List<CardapioItemDTO> listarItensAtivos() {
        return repository.findByAtivoTrue()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CardapioItemDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }
}