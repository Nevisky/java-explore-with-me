package ru.practicum.event.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.category.model.Category;
import ru.practicum.event.model.Event;
import ru.practicum.event.util.EventState;
import ru.practicum.user.model.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByInitiatorId(Long userId, PageRequest pageRequest);

    @Modifying
    @Query(value = "UPDATE event " +
            "SET confirmed_requests = ?1 " +
            "WHERE id = ?2 ",
            nativeQuery = true)
    void updateConfirmedRequest(int confirmedRequest, Long eventId);

/*
    @Query("SELECT e FROM Event e " +
            "WHERE (COALESCE(:users, NULL) IS NULL OR e.initiator.id IN :users) " +
            "AND (COALESCE(:states, NULL) IS NULL OR e.state IN :states) " +
            "AND (COALESCE(:categories, NULL) IS NULL OR e.category.id IN :categories) " +
            "AND (COALESCE(:rangeStart, NULL) IS NULL OR e.eventDate >= :rangeStart) " +
            "AND (COALESCE(:rangeEnd, NULL) IS NULL OR e.eventDate <= :rangeEnd) ")
    List<Event> findAllByAdmin(@Param("users") List<Long> users,
                               @Param("states") List<EventState> states,
                               @Param("categories") List<Long> categories,
                               @Param("rangeStart") LocalDateTime rangeStart,
                               @Param("rangeEnd") LocalDateTime rangeEnd,
                               PageRequest page);


 */

    List<Event> getEventByInitiatorInAndStateInAndCategoryInAndEventDateBetween(List<User> users, List<EventState> states, List<Category> categories, LocalDateTime start, LocalDateTime end, PageRequest pageRequest);

    List<Event> findByInitiatorIdIn(List<Long> users, PageRequest pageRequest);

    List<Event> searchEventsByAnnotationContainsOrDescriptionContainsAndCategoryIdInAndPaidAndCreatedOnBetween(String annotation, String description, Collection<Long> categoryId, Boolean paid, LocalDateTime start, LocalDateTime end, PageRequest pageRequest);

    Optional<Event> findByCategory(Category category);

    List<Event> findAllByIdIn(List<Long> id);
}
