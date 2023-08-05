package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.HitDto;
import ru.practicum.HitResponseDto;
import ru.practicum.mapper.HitMapper;
import ru.practicum.model.Hit;
import ru.practicum.repository.StatRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatServiceImpl implements StatService {


    private final StatRepository statRepository;

    @Override
    public void addHit(HitDto hitDto) {
        Hit hit = HitMapper.toHit(hitDto);
        statRepository.save(hit);
        log.info("Информация по endpoint = {} сохранена", hit.getUri());
    }

    @Override
    public List<HitResponseDto> getAllStatistic(LocalDateTime start, LocalDateTime end, boolean unique, List<String> uris) {
        Sort sort = Sort.by(Sort.Direction.DESC,"uri");
        if (uris == null) {
            return statRepository.findAll(sort)
                    .stream()
                    .map((Hit hit) -> HitMapper.toHitResponseDto(hit, this.statRepository.count())).collect(Collectors.toList());

        } else if (unique) {
            return statRepository.findDistinctByUriNotIn(uris).stream()
                    .map((Hit hit) -> HitMapper.toHitResponseDto(hit, statRepository.countHitsByUriIsNotIn(uris)))
                    .collect(Collectors.toList());
        }
        log.info("Получена инфомрация по endpoint = {}", uris);
        return statRepository.findViewStats(start, end, uris);

    }

}
