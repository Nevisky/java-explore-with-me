package ru.practicum.category.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategoryDto {


    Long id;

    @NotBlank
    @Length(max = 50, min = 1)
    String name;
}
