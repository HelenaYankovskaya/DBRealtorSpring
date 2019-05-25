package com.it.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @Column(unique = true, nullable = false)
    @NotNull(message = "{serviceModel.serviceModel.notNull}")
    @NotEmpty(message = "{serviceModel.serviceModel.notEmpty}")
    @Size(min = 3, max = 50, message = "{serviceModel.serviceModel.size}")
    private String serviceModel;

    @Column(unique = true, nullable = false)
    @NotNull(message = "{serviceModel.serviceValue.notNull}")
    private Long serviceValue;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "ServiceModel_has_Client",
            joinColumns = {@JoinColumn(name = "id_service")},
            inverseJoinColumns = {@JoinColumn(name = "client_id")})
    private Set<Client> client;

    public ServiceModel() {
    }

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
