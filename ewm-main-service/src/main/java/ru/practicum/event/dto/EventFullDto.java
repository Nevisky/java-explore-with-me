package ru.practicum.event.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.location.model.Location;
import ru.practicum.user.dto.UserShortDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EventFullDto {

    @Size(max = 2001, min = 20)
    String annotation;

    CategoryDto category;

    int confirmedRequests;

    String createdOn;

    @NotNull
    @Size(max = 7001, min = 20)
    String description;

    String eventDate;

    Long id;

    UserShortDto initiator;

    Location location;

    Boolean paid;

    Integer participantLimit;

    String publishedOn;

    Boolean requestModeration;

    String state;

    @Length(max = 121, min = 3)
    String title;

    Long views;

}
