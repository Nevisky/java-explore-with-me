package ru.practicum;

import lombok.*;
import lombok.experimental.FieldDefaults;


@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class HitResponseDto {

    String app;

    String uri;

    int hits;
}
