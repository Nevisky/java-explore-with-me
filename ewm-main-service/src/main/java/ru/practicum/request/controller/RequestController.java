package ru.practicum.request.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.request.dto.EventRequestStatusUpdateRequest;
import ru.practicum.request.dto.EventRequestStatusUpdateResult;
import ru.practicum.request.dto.ParticipationRequestDto;
import ru.practicum.request.service.RequestService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/users")
public class RequestController {

    private final RequestService requestService;

    @PostMapping("{userId}/requests")
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto addRequestByCurrentUser(@PathVariable("userId") Long userId,
                                                           @RequestParam Long eventId) {
        log.info("Создан запрос на участе в событии c id {} от пользователя с id {}", userId, eventId);
    return requestService.addRequestByCurrentUser(userId, eventId);
}

    @PatchMapping("/{userId}/requests/{requestId}/cancel")
    public ParticipationRequestDto patchRequestByCurrentUser(@PathVariable("userId") Long userId,
                                                             @PathVariable("requestId") Long requestId) {
        return requestService.patchRequestByCurrentUser(userId, requestId);
    }

    @GetMapping("{userId}/requests")
    public List<ParticipationRequestDto> findInformationAboutRequestByCurrentUser(@PathVariable("userId") Long userId) {
    log.info("Запрос на получение информации о заявках текущего пользователя на участие в чужих событиях");
        return requestService.findInformationAboutRequestByCurrentUser(userId);
    }

    @PatchMapping("/{userId}/events/{eventId}/requests")
    public EventRequestStatusUpdateResult patchStatusRequestByCurrentUser(@RequestBody EventRequestStatusUpdateRequest eventRequestsStatusUpdateRequest,
                                                                          @PathVariable("userId") Long userId,
                                                                          @PathVariable("eventId") Long eventId) {
        return requestService.patchStatusRequestByCurrentUser(eventRequestsStatusUpdateRequest, userId, eventId);
    }

}
