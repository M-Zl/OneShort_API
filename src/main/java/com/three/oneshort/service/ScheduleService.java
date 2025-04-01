package com.three.oneshort.service;

import com.three.oneshort.entity.Schedule;
import com.three.oneshort.entity.User;
import com.three.oneshort.repository.ScheduleRepository;
import com.three.oneshort.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.three.oneshort.dto.ScheduleRequestDto;
import com.three.oneshort.dto.ScheduleUpdateDto;


import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    public List<Schedule> getTodaySchedules(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
        LocalDate today = LocalDate.now();
        return scheduleRepository.findAllByUserAndDate(user, today);
    }

    public Schedule createSchedule(ScheduleRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));

        Schedule schedule = new Schedule();
        schedule.setUser(user);
        schedule.setTitle(requestDto.getTitle());
        schedule.setDescription(requestDto.getDescription());
        schedule.setDate(requestDto.getDate());
        schedule.setTime(requestDto.getTime());

        return scheduleRepository.save(schedule);
    }

    public Schedule updateSchedule(Long scheduleId, ScheduleUpdateDto dto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        schedule.setTitle(dto.getTitle());
        schedule.setDescription(dto.getDescription());
        schedule.setDate(dto.getDate());
        schedule.setTime(dto.getTime());

        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long scheduleId) {
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new IllegalArgumentException("해당 일정이 존재하지 않습니다.");
        }
        scheduleRepository.deleteById(scheduleId);
    }

}