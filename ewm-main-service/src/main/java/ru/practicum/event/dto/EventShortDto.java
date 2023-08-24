package ru.practicum.event.dto;

import lombok.*;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.user.dto.UserShortDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EventShortDto {

    String annotation;

    CategoryDto category;

    int confirmedRequests;

    String eventDate;

    Long id;

    UserShortDto initiator;

    Boolean paid;

    String title;

    Long views;

}
