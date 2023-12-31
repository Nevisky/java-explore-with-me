package ru.practicum.request.service;

import ru.practicum.request.dto.EventRequestStatusUpdateRequest;
import ru.practicum.request.dto.EventRequestStatusUpdateResult;
import ru.practicum.request.dto.ParticipationRequestDto;
import ru.practicum.request.model.Request;

import java.util.List;

public interface RequestService {
    ParticipationRequestDto addRequestByCurrentUser(Long userId, Long eventId);

    Request validateRequest(Long requestId);

    ParticipationRequestDto patchRequestByCurrentUser(Long userId, Long requestId);

    List<ParticipationRequestDto> findInformationAboutRequestByCurrentUser(Long userId);

    EventRequestStatusUpdateResult patchStatusRequestByCurrentUser(EventRequestStatusUpdateRequest eventRequestsStatusUpdateRequest, Long userId, Long eventId);
}
