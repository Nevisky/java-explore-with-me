package ru.practicum.location.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LocationDto {

    Float lat;

    Float lon;
}
