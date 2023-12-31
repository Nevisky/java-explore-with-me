package ru.practicum.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.request.model.Request;
import ru.practicum.user.model.User;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByRequesterId(Long userId);

    List<Request> findByRequester(User requester);

    List<Request> findByEventId(Long eventId);

     Request findByEventIdAndRequesterId(Long eventId, Long userId);



}
