package com.it.app.model;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "flat",fetch = FetchType.EAGER)
    private Set<Contracts> contracts;

    private String adress;
    private Double square;
    private Boolean isBalcon;
    private Long numberRooms;
    private Long value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repair_id")
    private Repair repair;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "walls_id")
    private Walls walls;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns(@JoinColumn(name = "recommendedValue_id"))
    private RecommendedValue recommendedValue;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Contracts> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contracts> contracts) {
        this.contracts = contracts;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public Boolean getBalcon() {
        return isBalcon;
    }

    public void setBalcon(Boolean balcon) {
        isBalcon = balcon;
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

    public void setPlan(Plan planing) {
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

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", contracts=" + contracts +
                ", adress='" + adress + '\'' +
                ", square=" + square +
                ", isBalcon=" + isBalcon +
                ", numberRooms=" + numberRooms +
                ", value=" + value +
                ", plan=" + plan +
                ", repair=" + repair +
                ", walls=" + walls +
                ", recommendedValue=" + recommendedValue +
                '}';
    }
}
