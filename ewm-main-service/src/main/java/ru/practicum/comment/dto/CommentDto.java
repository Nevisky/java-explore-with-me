package ru.practicum.comment.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDto {

    Long id;
    @NotBlank(message = "Комментарий не может быть пустым")
    String text;

    Long authorId;

    Long eventId;

    LocalDateTime created;

    LocalDateTime edited;

}