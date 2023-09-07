package com.example.assignment.service;

import com.example.assignment.domain.Item;
import com.example.assignment.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void addItem(ItemDto itemDto){
        Item item = itemDto.toEntity();

        validateItemName(item);

        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, ItemDto itemDto){
        Item item = findItem(itemId);

        item.updateItem(itemDto);
    }

    @Transactional
    public void deleteItem(Long itemId) {
        Item item = findItem(itemId);


    }

    private Item findItem(Long itemId){
        // TODO: 커스텀 예외로 변경하기
        return itemRepository.findById(itemId)
                .orElseThrow(IllegalArgumentException::new);
    }

    private void validateItemName(Item item){
        Optional<Item> byName = itemRepository.findByName(item.getName());

        if(byName.isPresent()){
            //TODO : 커스텀 예외 사용하기
            throw new IllegalArgumentException("(임시 예외) 상품 이름 중복");
        }
    }
}
