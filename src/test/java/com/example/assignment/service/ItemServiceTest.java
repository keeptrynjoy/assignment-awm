package com.example.assignment.service;

import com.example.assignment.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@Slf4j
@DataJpaTest
public class ItemServiceTest {

    @Autowired
    private ItemRepository itemRepository;

    private ItemService itemService;

}