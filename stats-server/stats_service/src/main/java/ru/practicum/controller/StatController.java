package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.HitDto;
import ru.practicum.HitResponseDto;
import ru.practicum.service.StatServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StatController {

    private final StatServiceImpl service;

    @PostMapping("/hit")
    public ResponseEntity<String> createHit(@RequestBody HitDto hitDto) {

        log.info("Создан запрос на сохранение информации к эндпоинту ");
        service.addHit(hitDto);
        return new ResponseEntity<>("Информация сохранена", HttpStatus.ACCEPTED);
    }

    @GetMapping("/stats")
    public List<HitResponseDto> getAllStatistic(@RequestParam(name = "start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                                @RequestParam(name = "end")
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                                @RequestParam(name = "unique", defaultValue = "false") Boolean unique,
                                                @RequestParam(name = "uris", required = false) List<String> uris) {
        log.info("Создан запрос на получение статистики посещаемости");
        return service.getAllStatistic(start, end, unique, uris);
    }
}
