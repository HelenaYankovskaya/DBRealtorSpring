package com.it.app.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class WallsDto {

    /**
     * Data Transfer Object class for Walls entity
     */
    private Long id;

    @NotNull(message = "{walls.walls.notNull}")
    @NotEmpty(message = "{walls.walls.notEmpty}")
    @Size(min = 3, max = 50, message = "{walls.walls.size}")
    private String walls;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWalls() {
        return walls;
    }

    public void setWalls(String walls) {
        this.walls = walls;
    }
}
