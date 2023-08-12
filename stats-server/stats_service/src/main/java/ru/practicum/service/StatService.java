package ru.practicum.service;

import ru.practicum.HitDto;
import ru.practicum.HitResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatService {

    void addHit(HitDto statDto);

    List<HitResponseDto> getAllStatistic(LocalDateTime start, LocalDateTime end, boolean unique, List<String> uris);

}
