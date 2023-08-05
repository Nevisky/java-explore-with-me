package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.HitResponseDto;
import ru.practicum.model.Hit;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface StatRepository extends JpaRepository<Hit, Long> {

    Long countHitsByUriIsNotIn(Collection<String> uri);

    List<Hit> findDistinctByUriNotIn(List<String> uri);

    @Query("SELECT new ru.practicum.HitResponseDto(h.app, h.uri, COUNT(h)) " +
            "FROM Hit h " +
            "WHERE h.timestamp BETWEEN ?1 AND ?2 AND h.uri IN (?3) " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(h) DESC"
    )
    List<HitResponseDto> findViewStats(LocalDateTime start, LocalDateTime end, List<String> uris);


}
