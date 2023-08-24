package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.HitDto;
import ru.practicum.HitResponseDto;
import ru.practicum.service.StatServiceImpl;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StatController {

    private final StatServiceImpl service;

    @PostMapping("/hit")
    public ResponseEntity<HitDto> createHit(@RequestBody HitDto hitDto) {
        log.info("Создан запрос на сохранение информации к эндпоинту");
        return new ResponseEntity<>( service.addHit(hitDto), HttpStatus.CREATED);
    }

    @GetMapping("/stats")
    public List<HitResponseDto> getAllStatistic(@RequestParam(value = "start") String start,
                                                @RequestParam(value = "end") String end,
                                                @RequestParam(value = "uris", defaultValue = "") List<String> uris,
                                                @RequestParam(value = "unique", defaultValue = "false") boolean unique) {
        log.info("Создан запрос на получение статистики посещаемости");
        return service.getStatsFromDB(start, end, uris, unique);
    }
}
