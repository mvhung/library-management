package com.app.library.model;

import com.app.library.model.role.RoleName;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id")
    private int userId;

    @Column(name = "us_name", nullable = false, length = 45)
    private String username;

    @Column(name = "us_password", nullable = false)
    private String password;
    @Column(name = "us_fullname", nullable = false)
    private String fullName;
    @Column(name = "us_address", nullable = false)
    private String address;
    @Column(name = "us_create_date")
    private Date createDate;
    @Column(name = "us_update_password")
    private int updatePassword;
    @Column(name = "us_mobile", nullable = false, length = 15)
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(name = "us_status", nullable = false)

    private RoleName status;
    @Column(name = "us_group")
    private int group;
    @Column(name = "us_email", nullable = false)
    private String email;


    }
