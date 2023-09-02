package ru.practicum.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.comment.dto.CommentDto;
import ru.practicum.comment.mapper.CommentMapper;
import ru.practicum.comment.model.Comment;
import ru.practicum.comment.repository.CommentRepository;
import ru.practicum.event.model.Event;
import ru.practicum.event.service.EventService;
import ru.practicum.event.util.EventState;
import ru.practicum.exception.ObjectNotFoundException;
import ru.practicum.request.model.Request;
import ru.practicum.request.repository.RequestRepository;
import ru.practicum.request.util.RequestStatus;
import ru.practicum.user.model.User;
import ru.practicum.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CommentServiceImpl implements CommentService {

    private final UserService userService;
    private final EventService eventService;
    private final RequestRepository requestRepository;
    private final CommentRepository commentRepository;

    public Comment validateComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new ObjectNotFoundException(String.format("Комментария с id = %d не существует", commentId)));
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Long userId, Long eventId) {
        User user = userService.validateUser(userId);
        Event event = eventService.validatedEvent(eventId);
        if (event.getState() != EventState.PUBLISHED) {
            throw new ObjectNotFoundException("Событие должно быть опубликовано");
        }
       Request request = requestRepository.findByEventIdAndRequesterId(eventId, userId);
        if (request.getStatus() != RequestStatus.CONFIRMED) {
            throw new ObjectNotFoundException(String.format("Пользователь c id = %d не подтвердил свою заявку на участие " +
                    "в событии с id = %d", userId, eventId));
        }

       Comment comment =  commentRepository.save(CommentMapper.toComment(commentDto, user, event));

        return CommentMapper.toCommentDto(comment);
    }

    @Override
    public CommentDto updateComment(Long userId, Long eventId, Long commentId, CommentDto commentDto) {
        User user = userService.validateUser(userId);
        Event event = eventService.validatedEvent(eventId);
        eventService.validatedEvent(eventId);
        Comment comment = validateComment(commentId);
        if (!userId.equals(comment.getAuthor().getId())) {
            throw new ObjectNotFoundException("Вы не явлетесь автором комментария");
        }
        Comment updatedComment = commentRepository.save(CommentMapper.toCommentUpdated(commentDto, comment, user, event));

        return CommentMapper.toCommentDto(updatedComment);
    }

    @Override
    public void deleteComment(Long userId, Long eventId, Long commentId) {
        Comment comment = validateComment(commentId);
        if (!userId.equals(comment.getAuthor().getId())) {
            throw new IllegalArgumentException("Вы не явлетесь автором комментария");
        }
        commentRepository.delete(comment);
    }

    @Override
    public void deleteCommentByAdmin(Long commentId) {
        Comment comment = validateComment(commentId);
        commentRepository.delete(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> findAllCommentsByEvent(Long eventId, Integer from, Integer size) {
        PageRequest page = PageRequest.of(from / size, size);
        List<Comment> commentList = commentRepository.findAllByEventId(eventId, page);
        return commentList.stream().map(CommentMapper::toCommentDto).collect(Collectors.toList());
    }

}
