package com.it.app.dto.response;

/**
 * Response Data Transfer Realtor entity to  Object class
 */
public class RealtorResponseDto {

    private Long id;

    private String post;

    private UserResponseDto user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }
}
