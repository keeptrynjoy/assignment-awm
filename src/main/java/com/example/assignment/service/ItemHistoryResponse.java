package com.example.assignment.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ItemHistoryResponse {
    private List<ItemHistoryDto> itemHistoryList;
    private int pageNumber;
    private int totalPages;

    public static ItemHistoryResponse toResponse(List<ItemHistoryDto> list, int pageNumber, int totalPages){
        return new ItemHistoryResponse(list,pageNumber,totalPages);
    }
}
