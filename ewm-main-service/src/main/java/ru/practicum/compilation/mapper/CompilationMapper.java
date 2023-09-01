package ru.practicum.compilation.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.compilation.dto.CompilationDto;
import ru.practicum.compilation.dto.NewCompilationDto;
import ru.practicum.compilation.model.Compilation;
import ru.practicum.event.mapper.EventMapper;
import ru.practicum.event.model.Event;

import java.util.Collections;
import java.util.stream.Collectors;

@UtilityClass
public class CompilationMapper {

    public static CompilationDto toCompilationDto(Compilation compilation) {
        return CompilationDto.builder()
                .id(compilation.getId())
                .pinned(compilation.getPinned() != null ? compilation.getPinned() : false)
                .title(compilation.getTitle())
                .events(compilation.getEvents() != null ? compilation.getEvents().stream()
                                .map(EventMapper::toEventShortDto)
                                .collect(Collectors.toList()) : Collections.emptyList()
                )
                .build();
    }

    public static Compilation toCompilation(NewCompilationDto newCompilationDto) {
        Compilation.CompilationBuilder compilationBuilder =
                Compilation.builder()
                        .pinned(newCompilationDto.getPinned() != null ? newCompilationDto.getPinned() : false)
                        .title(newCompilationDto.getTitle());

        if (newCompilationDto.getEvents() != null && !newCompilationDto.getEvents().isEmpty())
            compilationBuilder.events(
                    newCompilationDto.getEvents()
                            .stream().map(eventId -> Event.builder().id(eventId).build())
                            .collect(Collectors.toSet())
            );
        return compilationBuilder.build();
    }
}
