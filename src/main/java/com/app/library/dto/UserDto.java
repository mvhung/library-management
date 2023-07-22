package com.app.library.dto;

import com.app.library.model.role.RoleName;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.app.library.model.User}
 */
@Data
@AllArgsConstructor
public class UserDto implements Serializable {
    String username;
    String fullName;
    String address;
    String mobile;
    RoleName status;
    String email;
}