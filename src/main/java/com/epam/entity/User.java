package com.epam.entity;

import javax.jws.soap.SOAPBinding;

public class User extends BaseEntity {
    private String login;
    private String password;
    private Role role;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        User user = (User) obj;
        if (login != null && user.login != null) {
            if (!login.equals(user.login)) return false;
        }
        if (password != null && user.password != null) {
            if (!password.equals(user.password)) return false;
        }
        if (role != null && user.role != null) {
            if (!role.equals(user.role)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int defaul = 0;
        int h = 1;
        h = 31 * h + login != null ? login.hashCode() : defaul;
        h = 31 * h + password != null ? login.hashCode() : defaul;
        return h;
    }
}
