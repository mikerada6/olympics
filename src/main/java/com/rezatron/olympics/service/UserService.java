package com.rezatron.olympics.service;

import com.rezatron.olympics.dto.UserDto;
import com.rezatron.olympics.dto.mapper.UserMapper;
import com.rezatron.olympics.model.User;
import com.rezatron.olympics.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    public List<UserDto> getAll() {
        List<User> l = userRepository.findAll();
        return l.stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    public UserDto save(UserDto user) {
        User u = userRepository.save(userMapper.userDtoToUser(user));
        return userMapper.userToUserDto(u);
    }
}
