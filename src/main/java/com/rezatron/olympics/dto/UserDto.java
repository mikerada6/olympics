package com.rezatron.olympics.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.rezatron.olympics.model.User}
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    String firstName;
    @NotNull
    String lastName;
}