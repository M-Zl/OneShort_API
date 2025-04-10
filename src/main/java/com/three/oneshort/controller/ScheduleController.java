package com.three.oneshort.controller;

import com.three.oneshort.entity.Schedule;
import com.three.oneshort.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.three.oneshort.dto.ScheduleRequestDto;
import com.three.oneshort.dto.ScheduleUpdateDto;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/today")
    public List<Schedule> getTodaySchedules(@RequestParam Long userId) {
        return scheduleService.getTodaySchedules(userId);
    }

    @PostMapping
    public Schedule createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    @PutMapping("/{scheduleId}")
    public Schedule updateSchedule(@PathVariable Long scheduleId,
                                   @RequestBody ScheduleUpdateDto dto) {
        return scheduleService.updateSchedule(scheduleId, dto);
    }

    @DeleteMapping("/{scheduleId}")
    public String deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return "삭제 완료!";
    }
}