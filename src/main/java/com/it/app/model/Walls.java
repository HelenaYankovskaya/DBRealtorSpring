package com.it.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Class for creating the table Walls
 */
@Entity
@Table
public class Walls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull(message = "{walls.walls.notNull}")
    @NotEmpty(message = "{walls.walls.notEmpty}")
    @Size(min = 3, max = 50, message = "{walls.walls.size}")
    private String walls;

    @OneToMany(mappedBy = "walls")
    private Set<Flat> flats;

    public Walls() {
    }

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

    public Set<Flat> getFlats() {
        return flats;
    }

    public void setFlats(Set<Flat> flats) {
        this.flats = flats;
    }
}
