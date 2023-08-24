package ru.practicum.request.dto;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationRequestDto {

    String created;

    Long event;

    Long id;

    Long requester;

    String status;

}
