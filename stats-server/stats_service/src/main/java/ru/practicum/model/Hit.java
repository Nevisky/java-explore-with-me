package ru.practicum.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@Table(name = "HITS")
public class Hit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stat_id")
    Long id;

    @Column(name = "app")
    String app;

    @Column(name = "uri")
    String uri;

    @Column(name = "ip")
    String ip;

    @Column(name = "timestamp")
    LocalDateTime timestamp;

}
