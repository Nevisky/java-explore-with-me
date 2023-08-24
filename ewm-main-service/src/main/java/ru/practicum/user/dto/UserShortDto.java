package ru.practicum.user.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserShortDto {

    Long id;

    @NotBlank
    @NotEmpty
    @Length(max = 250, min = 2)
    String name;
}
