package com.it.app.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Class for creating the table Client
 */
@Entity
@Table
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passport;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns(@JoinColumn(name = "user_id"))
    private User user;

    @ManyToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<ServiceModel> services;


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ServiceModel> getServices() {
        return services;
    }

    public void setServices(Set<ServiceModel> services) {
        this.services = services;
    }
}
