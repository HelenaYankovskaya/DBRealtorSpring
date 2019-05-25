package com.it.app.dto.response;

/**
 * Response Data Transfer Client entity to  Object class
 */
public class ClientResponseDto {

    private Long id;

    private String passport;

    private UserResponseDto user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }
}
