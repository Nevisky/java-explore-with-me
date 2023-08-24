package ru.practicum.request.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.event.model.Event;
import ru.practicum.request.dto.ParticipationRequestDto;
import ru.practicum.request.model.Request;
import ru.practicum.request.util.RequestStatus;
import ru.practicum.user.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class RequestMapper {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Request toRequest(ParticipationRequestDto participationRequestDto, Event event, User user) {
        return Request.builder()
                .event(event)
                .requester(user)
                .status(RequestStatus.changeStringToState(participationRequestDto.getStatus()))
                .created(LocalDateTime.parse(participationRequestDto.getCreated()))
                .build();
    }

    public static Request newRequest(Event event, User user) {
        return Request.builder()
                .event(event)
                .requester(user)
                .created(LocalDateTime.now())
                .build();
    }

    public static ParticipationRequestDto toParticipationRequestDto(Request request) {
        return ParticipationRequestDto.builder()
                .id(request.getId())
                .event(request.getEvent().getId())
                .requester(request.getRequester().getId())
                .status(request.getStatus().toString())
                .created(formatter.format(request.getCreated()))
                .build();
    }
}
