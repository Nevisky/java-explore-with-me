package ru.practicum.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.HitDto;
import ru.practicum.HitResponseDto;
import ru.practicum.model.Hit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class HitMapper {

    public static Hit toHit(HitDto hitDto) {
        return Hit.builder()
                .app(hitDto.getApp())
                .uri(hitDto.getUri())
                .ip(hitDto.getIp())
                .timestamp(LocalDateTime.parse(hitDto.getTimestamp(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }

    public static HitDto toHitDto(Hit hit) {
        return HitDto.builder()
                .id(hit.getId())
                .app(hit.getApp())
                .uri(hit.getUri())
                .ip(hit.getIp())
                .timestamp(hit.getTimestamp().toString())
                .build();

    }

    public static HitResponseDto toHitResponseDto(Hit hit, Long hits) {
        return HitResponseDto.builder()
                .app(hit.getApp())
                .uri(hit.getUri())
                .hits(hits)
                .build();

    }
}
