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
public class HitResponseDto {

    String app;

    String uri;

    Long hits;
}
