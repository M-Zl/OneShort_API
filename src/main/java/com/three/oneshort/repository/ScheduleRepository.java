package com.three.oneshort.repository;

import com.three.oneshort.entity.Schedule;
import com.three.oneshort.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByUserAndDate(User user, LocalDate date);
    List<Schedule> findByUserIdAndDate(Long userId, LocalDate date);
}