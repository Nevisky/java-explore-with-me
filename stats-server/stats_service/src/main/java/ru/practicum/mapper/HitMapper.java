package ru.practicum.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.HitDto;
import ru.practicum.HitResponseDto;
import ru.practicum.model.Hit;


@UtilityClass
public class HitMapper {
    public static Hit toHit(HitDto hitDto) {
        return Hit.builder()
                .app(hitDto.getApp())
                .uri(hitDto.getUri())
                .ip(hitDto.getIp())
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

    public static HitResponseDto toHitResponseDto(Hit hit, Integer hits) {
        return HitResponseDto.builder()
                .app(hit.getApp())
                .uri(hit.getUri())
                .hits(hits)
                .build();

    }
}
