package ru.practicum.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comment.service.CommentService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/comments")
@Slf4j
public class AdminCommentController {

    private final CommentService commentService;

    @DeleteMapping("{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeComment(@PathVariable Long commentId) {
        log.info("Создан запрос на удаление комментария администратором");
        commentService.deleteCommentByAdmin(commentId);
    }
}
