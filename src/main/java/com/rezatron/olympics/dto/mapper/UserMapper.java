package com.rezatron.olympics.dto.mapper;

import com.rezatron.olympics.dto.UserDto;
import com.rezatron.olympics.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
