package com.it.app.dto.response;

import com.it.app.dto.PlanDto;
import com.it.app.dto.RepairDto;
import com.it.app.dto.WallsDto;

/**
 * Response Data Transfer Flat entity to  Object class
 */
public class FlatResponseDto {

    private Long id;

    private String address;

    private Long square;

    private Boolean isBalcony;

    private Long numberRooms;

    private Long value;

    private PlanDto plan;

    private RepairDto repair;

    private WallsDto walls;

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

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public PlanDto getPlan() {
        return plan;
    }

    public void setPlan(PlanDto plan) {
        this.plan = plan;
    }

    public RepairDto getRepair() {
        return repair;
    }

    public void setRepair(RepairDto repair) {
        this.repair = repair;
    }

    public WallsDto getWalls() {
        return walls;
    }

    public void setWalls(WallsDto walls) {
        this.walls = walls;
    }
}
