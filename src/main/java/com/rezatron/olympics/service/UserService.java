package com.rezatron.olympics.service;

import com.rezatron.olympics.dto.UserDto;
import com.rezatron.olympics.model.User;
import com.rezatron.olympics.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;
    public List<UserDto> getAll() {
        List<UserDto> users = new ArrayList<>();
        List<User> l = userRepository.findAll();
        for(User u: l)
        {
            users.add(UserDto.builder().firstName(u.getFirstName()).lastName(u.getLastName()).build());
        }
        return users;
    }

    public UserDto save(UserDto user) {
        User userToSave = User.builder().firstName(user.getFirstName()).lastName(user.getLastName()).build();
        User u = userRepository.save(userToSave);
        return UserDto.builder().firstName(u.getFirstName()).lastName(u.getLastName()).build();
    }
}
