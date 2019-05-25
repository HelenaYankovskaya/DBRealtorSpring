package com.it.app.dto.request;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Request Data For converting Object class in Client entity
 */

public class ClientRequestDto {

    private Long id;

    @NotNull(message = "{client.passport.notNull}")
    @NotEmpty(message = "{client.passport.notEmpty}")
    @Size(min = 3, max = 10, message = "{client.passport.size}")
    private String passport;

    @NotNull(message = "{client.user.notNull}")
    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
