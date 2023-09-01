package ru.practicum.request.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.event.model.Event;
import ru.practicum.request.dto.ParticipationRequestDto;
import ru.practicum.request.model.Request;
import ru.practicum.user.model.User;
import ru.practicum.utility.TimeUtil;


import java.time.LocalDateTime;

@UtilityClass
public class RequestMapper {

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
                .created(TimeUtil.FORMATTER.format(request.getCreated()))
                .build();
    }
}
