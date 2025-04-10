package com.three.oneshort.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleRequestDto {
    private String title;
    private String description;
    private String date; // yyyy-MM-dd
    private String time; // HH:mm
    private Long userId;
}
