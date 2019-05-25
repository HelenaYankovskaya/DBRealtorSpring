package com.it.app.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Request Data For converting Object class in Realtor entity
 */
public class RealtorRequestDto {

    private Long id;

    @NotNull(message = "{realtor.post.notNull}")
    @NotEmpty(message = "{realtor.post.notEmpty}")
    @Size(min = 3, max = 50, message = "{realtor.post.size}")
    private String post;

    @NotNull(message = "{realtor.user.notNull}")
    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
