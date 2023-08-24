package ru.practicum.request.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EventRequestStatusUpdateRequest {

    List<Long> requestIds;

    String status;
}
