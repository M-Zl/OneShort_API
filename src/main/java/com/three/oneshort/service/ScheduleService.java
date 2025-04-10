package com.three.oneshort.service;

import com.three.oneshort.dto.ScheduleRequestDto;
import com.three.oneshort.dto.ScheduleUpdateDto;
import com.three.oneshort.entity.Schedule;
import com.three.oneshort.entity.User;
import com.three.oneshort.repository.ScheduleRepository;
import com.three.oneshort.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserRepository userRepository;

    public Schedule createSchedule(ScheduleRequestDto dto) {
        Optional<User> optionalUser = userRepository.findById(dto.getUserId());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("해당 유저가 존재하지 않습니다: " + dto.getUserId());
        }

        Schedule schedule = new Schedule();
        schedule.setTitle(dto.getTitle());
        schedule.setDescription(dto.getDescription());
        schedule.setDate(LocalDate.parse(dto.getDate()));
        schedule.setTime(LocalTime.parse(dto.getTime()));
        schedule.setUser(optionalUser.get());

        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getTodaySchedules(Long userId) {
        LocalDate today = LocalDate.now();
        return scheduleRepository.findByUserIdAndDate(userId, today);
    }

    public Schedule updateSchedule(Long id, ScheduleUpdateDto dto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다: " + id));

        schedule.setTitle(dto.getTitle());
        schedule.setDescription(dto.getDescription());
        schedule.setDate(LocalDate.parse(dto.getDate()));
        schedule.setTime(LocalTime.parse(dto.getTime()));

        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }
}
