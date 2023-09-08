package com.example.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ItemHistoryResponseDto {
    private List<ItemHistoryDto> itemHistoryList;
    private int pageNumber;
    private int totalPages;

    public static ItemHistoryResponseDto toResponse(List<ItemHistoryDto> list, int pageNumber, int totalPages){
        return new ItemHistoryResponseDto(list,pageNumber,totalPages);
    }
}
