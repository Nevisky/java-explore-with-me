package ru.practicum.compilation.dto;

import lombok.*;
import ru.practicum.event.dto.EventShortDto;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CompilationDto {

    private Long id;

    private boolean pinned;

    private String title;

    private List<EventShortDto> events;

}
