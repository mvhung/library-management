package com.app.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBorrowing {
    public int userId;
    public String firstName;
    public String lastName;
    public String userName;
}
