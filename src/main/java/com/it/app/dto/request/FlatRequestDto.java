package com.it.app.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Request Data For converting Object class in Flat entity
 */
public class FlatRequestDto {

    private Long id;

    @NotNull(message = "{flat.address.notNull}")
    @NotEmpty(message = "{flat.address.notEmpty}")
    @Size(min = 3, max = 50, message = "{flat.address.size}")
    private String address;

    @NotNull(message = "{flat.square.notNull}")
    private Long square;

    private Boolean isBalcony;

    @NotNull(message = "{flat.square.notNull}")
    private Long numberRooms;

    @NotNull(message = "{flat.value.notNull}")
    private Long value;

    private Long planId;

    private Long repairId;

    private Long wallsId;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getRepairId() {
        return repairId;
    }

    public void setRepairId(Long repairId) {
        this.repairId = repairId;
    }

    public Long getWallsId() {
        return wallsId;
    }

    public void setWallsId(Long wallsId) {
        this.wallsId = wallsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getSquare() {
        return square;
    }

    public void setSquare(Long square) {
        this.square = square;
    }

    public Boolean getBalcony() {
        return isBalcony;
    }

    public void setBalcony(Boolean balcony) {
        isBalcony = balcony;
    }

    public Long getNumberRooms() {
        return numberRooms;
    }

    public void setNumberRooms(Long numberRooms) {
        this.numberRooms = numberRooms;
    }
}
