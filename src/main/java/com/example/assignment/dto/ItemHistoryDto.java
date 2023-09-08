package com.example.assignment.dto;

import com.example.assignment.domain.ItemHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
public class ItemHistoryDto {

    private Long HistoryId;
    private Long itemId;
    private String itemName;
    private int price;
    private String issueAt;

    private static String dateToString(LocalDateTime issueAt){
        return issueAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static ItemHistoryDto toDto(ItemHistory itemHistory){
        String issueAt = dateToString(itemHistory.getIssueAt());

        return new ItemHistoryDto(
                itemHistory.getId(),
                itemHistory.getItem().getId(),
                itemHistory.getItem().getName(),
                itemHistory.getPrice(), issueAt);
    }

}
