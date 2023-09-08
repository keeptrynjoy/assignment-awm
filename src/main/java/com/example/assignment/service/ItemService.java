package com.example.assignment.service;

import com.example.assignment.domain.Item;
import com.example.assignment.domain.ItemHistory;
import com.example.assignment.domain.ItemStatus;
import com.example.assignment.repository.ItemHistoryRepository;
import com.example.assignment.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemHistoryRepository itemHistoryRepository;

    @Transactional
    public void addItem(ItemDto itemDto){
        Item item = itemDto.toEntity();

        validItemName(item);

        item.addHistory();

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

        item.softDeleteItem();
    }

    public Item findItem(Long itemId){
        // TODO : 커스텀 예외로 변경하기
        return itemRepository.findById(itemId)
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<ItemDto> findAllItem(){

        List<Item> all = itemRepository.findAll();
        List<ItemDto> itemDtoList = new ArrayList<>();
        for (Item item : all){
            if(!item.getStatus().equals(ItemStatus.DELETED))
                itemDtoList.add(ItemDto.toDto(item));
        }

        return itemDtoList;
    }

    public ItemHistoryResponse findItemHistoryList(Long itemId, int page){

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<ItemHistory> itemHistoryPage;

        if(Objects.nonNull(itemId)){
            itemHistoryPage = itemHistoryRepository.findByItemId(itemId, pageRequest);
        } else {
            itemHistoryPage = itemHistoryRepository.findAll(pageRequest);
        }

        List<ItemHistoryDto> itemHistoryDtoList = itemHistoryPage.get()
                        .map(ItemHistoryDto::toDto)
                        .collect(Collectors.toList());


        return ItemHistoryResponse
                .toResponse(itemHistoryDtoList, itemHistoryPage.getNumber(), itemHistoryPage.getTotalPages());
    }

    private void validItemName(Item item){
        Optional<Item> byName = itemRepository.findByName(item.getName());

        if(byName.isPresent()){
            //TODO : 커스텀 예외로 변경하기
            throw new IllegalArgumentException("이미 존재하는 상품의 이름입니다.");
        }
    }

}
