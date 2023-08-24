package ru.practicum.request.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.event.model.Event;
import ru.practicum.request.util.RequestStatus;
import ru.practicum.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "REQUESTS")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "created")
    LocalDateTime created;

    @JoinColumn(name = "event")
    @ManyToOne(fetch = FetchType.LAZY)
    Event event;

    @JoinColumn(name = "requester")
    @ManyToOne(fetch = FetchType.LAZY)
    User requester;

    @Column(name = "status")
    RequestStatus status;

}
