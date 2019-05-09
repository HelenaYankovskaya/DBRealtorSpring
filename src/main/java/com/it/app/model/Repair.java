package com.it.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Class for creating the table Repair
 */
@Entity
@Table
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "repair")
    private Set<Flat> flats;

    @Column(unique = true, nullable = false)
    @NotNull(message = "{repair.repair.notNull}")
    @NotEmpty(message = "{repair.repair.notEmpty}")
    @Size(min = 3, max = 50, message = "{repair.repair.size}")
    private String repair;

    public Repair() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Flat> getFlats() {
        return flats;
    }

    public void setFlats(Set<Flat> flats) {
        this.flats = flats;
    }

    public String getRepair() {
        return repair;
    }

    public void setRepair(String repair) {
        this.repair = repair;
    }
}
