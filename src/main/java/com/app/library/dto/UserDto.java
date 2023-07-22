package com.app.library.dto;

import com.app.library.model.role.RoleName;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.app.library.model.User}
 */
@Value
public class UserDto implements Serializable {
    String username;
    String fullName;
    String address;
    String mobile;
    RoleName status;
    String email;
}