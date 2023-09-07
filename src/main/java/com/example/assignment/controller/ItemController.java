package com.example.assignment.controller;

import com.example.assignment.service.ItemDto;
import com.example.assignment.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/item")
@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/")
    public ResponseEntity<Void> registerItem(@Valid @RequestBody ItemDto itemDto){

        itemService.addItem(itemDto);

        return ResponseEntity.ok().build();
    }

}
