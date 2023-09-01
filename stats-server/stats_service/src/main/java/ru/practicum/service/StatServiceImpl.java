package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.HitDto;
import ru.practicum.HitResponseDto;
import ru.practicum.exception.ValidationException;
import ru.practicum.mapper.HitMapper;
import ru.practicum.model.Hit;
import ru.practicum.repository.StatRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StatServiceImpl implements StatService {

    private final StatRepository statRepository;
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public HitDto addHit(HitDto hitDto) {
        Hit hit = HitMapper.toHit(hitDto);
        hit.setTimestamp(LocalDateTime.now());
        statRepository.save(hit);
        log.info("Информация по endpoint = {} сохранена", hit.getUri());
        return validationHit(hit.getId());
    }

    private HitDto validationHit(Long hitId) {
        return HitMapper.toHitDto(statRepository.findById(hitId).orElseThrow());

    }
    /*

    @Override
    @Transactional(readOnly = true)
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
        return statRepository.findViewHits(start, end, uris);

    }


     */
    @Override
    public List<HitResponseDto> getStatsFromDB(String start, String end, List<String> uris, boolean unique) {
        LocalDateTime from = LocalDateTime.parse(start, dateTimeFormatter);
        LocalDateTime to = LocalDateTime.parse(end, dateTimeFormatter);
        if (from.isAfter(to)) {
            throw new ValidationException("start is after end");
        }
        if (uris == null || uris.size() == 0 || uris.get(0).equals("events/") || uris.get(0).isBlank()) {
            uris = statRepository.getDistinctUri();
            log.info("Сколько было запросов {}", uris);
        }
        List<HitResponseDto> listDTO = new ArrayList<>();
        List<String> urisToListUnique;
        List<String> urisToList;
        if (unique) {
            urisToListUnique = statRepository.findUriByUniqueIp(uris, from, to);
            for (String uri : uris) {
                HitResponseDto statsDTO = new HitResponseDto("ewm-main-service", uri, Collections.frequency(urisToListUnique, uri));
                listDTO.add(statsDTO);
            }

        } else {
            urisToList = statRepository.getUrisByUri(uris, from, to);
            log.info("Сколько было запросов если не уникальный {}", urisToList);
            for (String uri : uris) {
                HitResponseDto statsDTO = new HitResponseDto("ewm-main-service", uri, Collections.frequency(urisToList, uri));
                listDTO.add(statsDTO);
        }

        }
        listDTO.sort((dto1, dto2) -> dto2.getHits() - dto1.getHits());
        log.info("listDTO = {}", listDTO);
        log.info("Данные " + (listDTO.get(0).getHits()));
        return listDTO;
    }

}