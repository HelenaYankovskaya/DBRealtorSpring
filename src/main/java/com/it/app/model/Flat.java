package com.it.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Class for creating the table Flat
 */
@Entity
@Table
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull(message = "{flat.address.notNull}")
    @NotEmpty(message = "{flat.address.notEmpty}")
    @Size(min = 3, max = 50, message = "{flat.address.size}")
    private String address;

    @Column(unique = true, nullable = false)
    @NotNull(message = "{flat.square.notNull}")
    private Long square;

    private Boolean isBalcony;

    @NotNull(message = "{flat.numberRooms.notNull}")
    private Long numberRooms;

    @NotNull(message = "{flat.value.notNull}")
    private Long value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPlan")
    private Plan plan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRepair")
    private Repair repair;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idWalls")
    private Walls walls;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns(@JoinColumn(name = "idRecommendedValue"))
    private RecommendedValue recommendedValue;

    @OneToMany(mappedBy = "flat")
    private Set<Contracts> contracts;

    public Set<Contracts> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contracts> contracts) {
        this.contracts = contracts;
    }

    public Flat() {
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

    public Boolean getBalcony() {
        return isBalcony;
    }

    public void setBalcony(Boolean balcony) {
        isBalcony = balcony;
    }

    public Long getSquare() {
        return square;
    }

    public void setSquare(Long square) {
        this.square = square;
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

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public Walls getWalls() {
        return walls;
    }

    public void setWalls(Walls walls) {
        this.walls = walls;
    }

    public RecommendedValue getRecommendedValue() {
        return recommendedValue;
    }

    public void setRecommendedValue(RecommendedValue recommendedValue) {
        this.recommendedValue = recommendedValue;
    }

}
