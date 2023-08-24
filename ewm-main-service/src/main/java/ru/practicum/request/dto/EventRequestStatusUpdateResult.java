package ru.practicum.request.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EventRequestStatusUpdateResult {

    List<ParticipationRequestDto> confirmedRequests;

   List<ParticipationRequestDto> rejectedRequests;

}
