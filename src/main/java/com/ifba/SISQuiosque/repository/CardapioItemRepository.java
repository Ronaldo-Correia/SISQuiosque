package com.ifba.SISQuiosque.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifba.SISQuiosque.entity.CardapioItem;
import java.util.List;

@Repository
public interface CardapioItemRepository extends JpaRepository<CardapioItem, Long> {
    List<CardapioItem> findByAtivoTrue();
}