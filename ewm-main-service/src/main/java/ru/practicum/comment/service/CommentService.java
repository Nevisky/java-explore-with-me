package ru.practicum.comment.service;

import ru.practicum.comment.dto.CommentDto;
import java.util.List;

public interface CommentService {


    CommentDto createComment(CommentDto commentDto, Long userId, Long eventId);

    CommentDto updateComment(Long userId, Long eventId, Long commentId, CommentDto commentDto);

    void deleteComment(Long userId, Long eventId, Long commentId);

    void deleteCommentByAdmin(Long commentId);

    List<CommentDto> findAllCommentsByEvent(Long eventId, Integer from, Integer size);

}
