package com.it.app.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Class for creating the table ServiceModel
 */
@Entity
@Table
public class ServiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceModel;

    private Long serviceValue;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "Service_Client",
            joinColumns = {@JoinColumn(name = "id_service")},
            inverseJoinColumns = {@JoinColumn(name = "client_id")})
    private Set<Client> client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceModel() {
        return serviceModel;
    }

    public void setServiceModel(String serviceModel) {
        this.serviceModel = serviceModel;
    }

    public Long getServiceValue() {
        return serviceValue;
    }

    public void setServiceValue(Long serviceValue) {
        this.serviceValue = serviceValue;
    }

    public Set<Client> getClient() {
        return client;
    }

    public void setClient(Set<Client> client) {
        this.client = client;
    }
}
