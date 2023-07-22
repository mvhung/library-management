package com.app.library.model;

import com.app.library.model.role.RoleName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "us_id")
    private int userID;

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

    public User(String username, String password, String fullName, String address, Date createDate, int updatePassword,
            String mobile, RoleName status, int group, String email) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.createDate = createDate;
        this.updatePassword = updatePassword;
        this.mobile = mobile;
        this.status = status;
        this.group = group;
        this.email = email;
    }

    public User() {

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getUpdatePassword() {
        return updatePassword;
    }

    public void setUpdatePassword(int updatePassword) {
        this.updatePassword = updatePassword;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public RoleName getStatus() {
        return status;
    }

    public void setStatus(RoleName status) {
        this.status = status;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", createDate=" + createDate +
                ", updatePassword=" + updatePassword +
                ", mobile='" + mobile + '\'' +
                ", status=" + status +
                ", group=" + group +
                ", email='" + email + '\'' +
                '}';
    }
}
