package ru.practicum.service;

import ru.practicum.HitDto;
import ru.practicum.HitResponseDto;

import java.util.List;

public interface StatService {

    HitDto addHit(HitDto statDto);

    List<HitResponseDto> getStatsFromDB(String start, String end, List<String> uris, boolean unique);
}
