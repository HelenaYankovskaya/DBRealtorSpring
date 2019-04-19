package com.it.app.model;

import javax.persistence.*;
import java.util.Set;

// класс, где создается таблица Service, ее столбцы и связи с другими таблицами БД
@Entity
@Table
public class ServiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String service;

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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Set<Client> getClient() {
        return client;
    }

    public void setClient(Set<Client> client) {
        this.client = client;
    }
}
