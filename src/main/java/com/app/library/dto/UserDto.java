package com.app.library.dto;

import com.app.library.model.role.RoleName;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.app.library.model.User}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    int userId;
    String password;
    String username;
    String fullName;
    String address;
    String mobile;
    RoleName status;
    String email;
}