package com.it.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class for creating the table Contracts
 */
@Entity
@Table
public class Contracts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRealtor")
    @NotNull(message = "{contracts.realtor.notNull}")
    private Realtor realtor;

    @Column(unique = true, nullable = false)
    @NotNull(message = "{contracts.data.notNull}")
    @NotEmpty(message = "{contracts.data.notEmpty}")
    @Size(min = 3, max = 10, message = "{contracts.data.size}")
    private String data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idFlat")
    @NotNull(message = "{contracts.flat.notNull}")
    private Flat flat;

    public Contracts() {
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlats(Flat flat) {
        this.flat = flat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Realtor getRealtor() {
        return realtor;
    }

    public void setRealtor(Realtor realtor) {
        this.realtor = realtor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
