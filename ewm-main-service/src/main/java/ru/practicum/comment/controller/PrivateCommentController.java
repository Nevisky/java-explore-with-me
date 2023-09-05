package ru.practicum.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comment.dto.CommentDto;
import ru.practicum.comment.service.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/event/{eventId}/comments")
@Slf4j
public class PrivateCommentController {

    private final CommentService commentService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(@RequestParam Long userId,
                                    @Valid @RequestBody CommentDto commentDto,
                                    @PathVariable Long eventId) {
        log.info("Запрос на создание комментария");
        return commentService.createComment(commentDto, userId, eventId);
    }

    @PatchMapping("{commentId}")
    public CommentDto updateComment(@RequestParam Long userId,
            @PathVariable Long eventId,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentDto commentDto) {
        log.info("Создан запрос на обновление комментария с id = {}", commentId);
        return commentService.updateComment(userId, eventId, commentId, commentDto);
    }

    @DeleteMapping("{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeComment(@RequestParam Long userId,
                              @PathVariable Long eventId,
                              @PathVariable Long commentId) {
        log.info("Создан запрос на удаление комментария с id = {}", commentId);
        commentService.deleteComment(userId, eventId, commentId);
    }

    @GetMapping()
    public List<CommentDto> findAllCommentsByEvent(@PathVariable Long eventId,
                                                   @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                   @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Создан запрос на получение всех комментарий события с id = {}", eventId);
       return commentService.findAllCommentsByEvent(eventId, from, size);
    }
}
