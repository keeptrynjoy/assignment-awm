package com.example.assignment.controller;

import com.example.assignment.commons.config.interceptor.Auth;
import com.example.assignment.domain.UserRole;
import com.example.assignment.service.ItemDto;
import com.example.assignment.service.ItemHistoryResponse;
import com.example.assignment.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/item")
@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

    @Auth(role = UserRole.ALL)
    @GetMapping("/list")
    public ResponseEntity<List<ItemDto>> readItem(){

        List<ItemDto> allItem = itemService.findAllItem();

        return ResponseEntity.ok(allItem);
    }


    @Auth(role = UserRole.MART)
    @PostMapping("/info")
    public ResponseEntity<Void> registerItem(@Valid @RequestBody ItemDto itemDto){

        itemService.addItem(itemDto);

        return ResponseEntity.ok().build();
    }

    @Auth(role = UserRole.MART)
    @PatchMapping("/{id}/info")
    public ResponseEntity<Void> modifyItem(@PathVariable(value = "id") Long itemId,
                                           @Valid @RequestBody ItemDto itemDto){

        itemService.updateItem(itemId, itemDto);

        return ResponseEntity.ok().build();
    }

    @Auth(role = UserRole.MART)
    @DeleteMapping("/{id}/info")
    public ResponseEntity<Void> removeItem(@PathVariable(value = "id") Long itemId){

        itemService.deleteItem(itemId);

        return ResponseEntity.ok().build();
    }

    @Auth(role = UserRole.MART)
    @GetMapping("/history/{pageNum}")
    public ResponseEntity<ItemHistoryResponse> readItemPriceHistory(
            @PathVariable(value = "pageNum") int pageNum,
            @RequestParam(required = false) Long itemId){

        ItemHistoryResponse itemHistory = itemService.findItemHistoryList(itemId, pageNum);

        return ResponseEntity.ok(itemHistory);
    }

}
