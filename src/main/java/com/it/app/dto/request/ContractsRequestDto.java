package com.it.app.dto.request;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Request Data For converting Object class in Contract entity
 */
public class ContractsRequestDto {

    private Long id;

    @NotNull(message = "{contracts.data.notNull}")
    @NotEmpty(message = "{contracts.data.notEmpty}")
    @Size(min = 3, max = 10, message = "{contracts.data.size}")
    private String data;

    private Long realtorId;

    private Long flatId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRealtorId() {
        return realtorId;
    }

    public void setRealtorId(Long realtorId) {
        this.realtorId = realtorId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getFlatId() {
        return flatId;
    }

    public void setFlatId(Long flatId) {
        this.flatId = flatId;
    }
}
