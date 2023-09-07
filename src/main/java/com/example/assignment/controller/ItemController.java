package com.example.assignment.controller;

import com.example.assignment.service.ItemDto;
import com.example.assignment.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{id}")
    public ResponseEntity<Void> modifyItem(@PathVariable(value = "id") Long itemId,
                                           @Valid @RequestBody ItemDto itemDto){

        itemService.updateItem(itemId, itemDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable(value = "id") Long itemId){

        itemService.deleteItem(itemId);

        return ResponseEntity.ok().build();
    }
}
