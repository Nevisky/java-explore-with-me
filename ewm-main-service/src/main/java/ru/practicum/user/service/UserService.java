package ru.practicum.user.service;

import ru.practicum.user.dto.UserDto;
import ru.practicum.user.model.User;

import java.util.List;

public interface UserService {

   UserDto addUser(UserDto userDto);

    List<UserDto> findAllUsers(int from, int size, List<Long> ids);

    void removeUser(Long userId);

    User validateUser(Long userId);
}
