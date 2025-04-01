package com.three.oneshort.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class ScheduleUpdateDto {
    private String title;
    private String description;
    private LocalDate date;
    private LocalTime time;
}
