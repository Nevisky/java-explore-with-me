package ru.practicum.utility;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.format.DateTimeFormatter;
@Getter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DateFormat {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
