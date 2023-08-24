package ru.practicum.compilation.dto;

import lombok.*;

import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NewCompilationDto {

    private Boolean pinned;

    @Size(min = 1, max = 50)
    private String title;

    private Set<Long> events;
}
