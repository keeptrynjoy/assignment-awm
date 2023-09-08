package com.example.assignment.repository;

import com.example.assignment.domain.ItemHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemHistoryRepository extends JpaRepository<ItemHistory, Long> {

    Page<ItemHistory> findByItemId(Long itemId, Pageable pageable);

    Page<ItemHistory> findAll(Pageable pageable);
}
