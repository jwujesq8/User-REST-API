package com.test.api.service.interfaces;

import com.test.api.dto.request.POSTUserRequestDto;
import com.test.api.dto.request.PUTUserRequestDto;
import com.test.api.dto.response.UserResponseDto;
import com.test.api.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {


    UserResponseDto getUserById(Long id);

    UserResponseDto addUser(POSTUserRequestDto postUserRequestDto);

    UserResponseDto updateUser(PUTUserRequestDto putUserRequestDto);

    UserResponseDto deleteUser(Long id);

    List<UserResponseDto> getAllUsers();

    List<UserResponseDto> deleteListOfUsersByStartAndEndId(Long startId, Long endId);

    List<UserResponseDto> deleteListOfUsersByStartIdAsc(Long startId);



    boolean existsByLoginAndPasswordIgnoreCase(String login, String password);

    boolean existsById(Long id);

    Optional<User> getUserByLogin(String login);
}
