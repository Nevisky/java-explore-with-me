package ru.practicum.event.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.mapper.CategoryMapper;
import ru.practicum.category.model.Category;
import ru.practicum.category.service.CategoryService;
import ru.practicum.event.dto.*;
import ru.practicum.event.model.Event;
import ru.practicum.event.util.EventState;
import ru.practicum.user.mapper.UserMapper;
import ru.practicum.user.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass

public class EventMapper {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Event toEvent(EventFullDto eventFullDto, User user, EventState state) {
        return Event.builder()
                .id(eventFullDto.getId())
                .annotation(eventFullDto.getAnnotation())
                .category(CategoryMapper.toCategory(eventFullDto.getCategory()))
                .confirmedRequests(eventFullDto.getConfirmedRequests())
                .createdOn(LocalDateTime.parse(eventFullDto.getCreatedOn(), formatter))
                .description(eventFullDto.getDescription())
                .eventDate(LocalDateTime.parse(eventFullDto.getEventDate(), formatter))
                .initiator(user)
                .location(eventFullDto.getLocation())
                .paid(eventFullDto.getPaid())
                .participantLimit(eventFullDto.getParticipantLimit())
                .publishedOn(LocalDateTime.parse(eventFullDto.getPublishedOn(),formatter))
                .requestModeration(eventFullDto.getRequestModeration())
                .state(state)
                .title(eventFullDto.getTitle())
                .views(eventFullDto.getViews())
                .build();
    }

    public static Event toEvent(NewEventDto newEventDto, User user, Category category) {
        return Event.builder()
                .annotation(newEventDto.getAnnotation())
                .category(category)
                .createdOn(LocalDateTime.now())
                .publishedOn(LocalDateTime.now())
                .description(newEventDto.getDescription())
                .eventDate(LocalDateTime.parse(newEventDto.getEventDate(), formatter))
                .location(newEventDto.getLocation())
                .paid(newEventDto.getPaid())
                .participantLimit(newEventDto.getParticipantLimit())
                .requestModeration(newEventDto.getRequestModeration())
                .title(newEventDto.getTitle())
                .initiator(user)
                .build();
    }

        public static EventFullDto toEventFullDto(Event event) {
            return EventFullDto.builder()
                    .id(event.getId())
                    .annotation(event.getAnnotation())
                    .category(CategoryMapper.toCategoryDto(event.getCategory()))
                    .confirmedRequests(event.getConfirmedRequests())
                    .createdOn(LocalDateTime.now().format(formatter))
                    .description(event.getDescription())
                    .eventDate(event.getEventDate().format(formatter))
                    .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                    .location(event.getLocation())
                    .paid(event.getPaid())
                    .participantLimit(event.getParticipantLimit())
                    .publishedOn(event.getPublishedOn() == null ? "" : event.getPublishedOn().format(formatter))
                    .requestModeration(event.getRequestModeration())
                    .state(event.getState().toString() == null ? "" : event.getState().toString())
                    .title(event.getTitle())
                    .views(event.getViews())
                    .build();
    }


    public static EventShortDto toEventShortDto(Event event) {
        return EventShortDto.builder()
                .id(event.getId())
                .annotation(event.getAnnotation())
                .category(CategoryMapper.toCategoryDto(event.getCategory()))
                .confirmedRequests(event.getConfirmedRequests())
                .eventDate(event.getEventDate().format(formatter))
                .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                .paid(event.getPaid())
                .title(event.getTitle())
                .views(event.getViews())
                .build();

    }

    public static EventFullDto toEventFullDto(EventFullDto eventFullDto, UpdateEventUserRequest updateEventUserRequest) {
        return EventFullDto.builder()
                .id(eventFullDto.getId())
                .annotation(updateEventUserRequest.getAnnotation() != null ? updateEventUserRequest.getAnnotation() : eventFullDto.getAnnotation())
                .description(updateEventUserRequest.getDescription() != null ? updateEventUserRequest.getDescription() : eventFullDto.getDescription())
                .eventDate(updateEventUserRequest.getEventDate() != null ? updateEventUserRequest.getEventDate() : eventFullDto.getEventDate())
                .location(updateEventUserRequest.getLocation() != null ? updateEventUserRequest.getLocation() : eventFullDto.getLocation())
                .paid(updateEventUserRequest.getPaid() != null ? updateEventUserRequest.getPaid() : eventFullDto.getPaid())
                .participantLimit(updateEventUserRequest.getParticipantLimit() != null ? updateEventUserRequest.getParticipantLimit() : eventFullDto.getParticipantLimit())
                .title(updateEventUserRequest.getTitle() != null ? updateEventUserRequest.getTitle() : eventFullDto.getTitle())
                .confirmedRequests(eventFullDto.getConfirmedRequests())
                .createdOn(eventFullDto.getCreatedOn())
                .initiator((eventFullDto.getInitiator()))
                .publishedOn(eventFullDto.getPublishedOn())
                .requestModeration(eventFullDto.getRequestModeration())
                .state(eventFullDto.getState())
                .views(eventFullDto.getViews())
                .build();
    }

    public static EventFullDto toUpdateEventAdminRequest(UpdateEventAdminRequest updateEventAdminRequest, Event event, CategoryDto categoryDto) {
        return EventFullDto.builder()
                .id(event.getId())
                .annotation(updateEventAdminRequest.getAnnotation() != null ? updateEventAdminRequest.getAnnotation() : event.getAnnotation())
                .category(categoryDto)
                .description(updateEventAdminRequest.getDescription() != null ? updateEventAdminRequest.getDescription() : event.getDescription())
                .eventDate(updateEventAdminRequest.getEventDate() != null ? updateEventAdminRequest.getEventDate() : (formatter.format(event.getEventDate())))
                .location(updateEventAdminRequest.getLocation() != null ? updateEventAdminRequest.getLocation() : event.getLocation())
                .paid(updateEventAdminRequest.getPaid() != null ? updateEventAdminRequest.getPaid() : event.getPaid())
                .participantLimit(updateEventAdminRequest.getParticipantLimit() != null ? updateEventAdminRequest.getParticipantLimit() : event.getParticipantLimit())
                .requestModeration(updateEventAdminRequest.getRequestModeration() != null ? updateEventAdminRequest.getRequestModeration() : event.getRequestModeration())
                .title(updateEventAdminRequest.getTitle() != null ? updateEventAdminRequest.getTitle() : event.getTitle())
                .confirmedRequests(event.getConfirmedRequests())
                .createdOn(formatter.format(event.getCreatedOn()))
                .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                .publishedOn(formatter.format(event.getPublishedOn()))
                .views(event.getViews())
                .build();
    }

}
