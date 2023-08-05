package ru.practicum.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.HitDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Service
public class StatsClient extends BaseClient {


    @Autowired
    public StatsClient(@Value("${SPRING_DATASOURCE_URL}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> saveEvent (HitDto hitDto) {
        return post("/hit", hitDto);
    }

    public ResponseEntity<Object> getAllStatistic (LocalDateTime start, LocalDateTime end, Boolean unique, List<String> uris) {
        Map<String, Object> parameters = Map.of(
                "start", start,
                "end", end,
                "uris", uris,
                "unique", unique);
        return get("/stats?start={start}&end={end}&uris={uris}&unique={unique}", parameters);
    }

    public ResponseEntity<Object> getStatisticWithUniqueIps (LocalDateTime start, LocalDateTime end, Boolean unique) {
        Map<String, Object> parameters = Map.of(
                "start", start,
                "end", end,
                "unique", unique);
        return get("/stats?start={start}&end={end}&unique={unique}", parameters);
    }

    public ResponseEntity<Object> getStatisticWithOnlyUris (LocalDateTime start, LocalDateTime end, List<String> uris) {
        Map<String, Object> parameters = Map.of(
                "start", start,
                "end", end,
                "uris", uris);
        return get("/stats?start={start}&end={end}&uris={uris}", parameters);
    }

}
