package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatRepository extends JpaRepository<Hit, Long> {
    @Query("select distinct s.uri " +
            "from Hit as s")
    List<String> getDistinctUri();
    @Query(value = "select t.uri " +
            "from ( select distinct on (s.ip) s.uri from HITS as s " +
            "where s.uri in (?1) " +
            "and s.timestamp > ?2 and s.timestamp < ?3) as t",
            nativeQuery = true)
    List<String> findUriByUniqueIp(List<String> uri, LocalDateTime from, LocalDateTime to);

    @Query("select s.uri " +
            "from Hit as s " +
            "where s.uri in (?1) " +
            "and s.timestamp > ?2 and s.timestamp < ?3")
    List<String> getUrisByUri(List<String> uris, LocalDateTime from, LocalDateTime to);
}
