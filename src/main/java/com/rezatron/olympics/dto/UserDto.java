package com.rezatron.olympics.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.rezatron.olympics.model.User}
 */
@Value
@Builder
public class UserDto implements Serializable {
    String firstName;
    @NotNull
    String lastName;
}