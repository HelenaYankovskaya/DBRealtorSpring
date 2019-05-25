package com.it.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Class for creating the table User
 */

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "user")
    private Client clients;

    @OneToOne(mappedBy = "user")
    private Realtor realtor;

    @Column(unique = true, nullable = false)
    @NotNull(message = "{user.name.notNull}")
    @NotEmpty(message = "{user.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{user.name.size}")
    private String name;

    @Column(unique = true, nullable = false)
    @NotNull(message = "{user.passport.notNull}")
    @NotEmpty(message = "{user.passport.notEmpty}")
    @Size(min = 3, max = 20, message = "{user.passport.size}")
    private String password;

    @ManyToOne
    @JoinColumn(name = "UserRoleId", nullable = false)
    private UserRole userRole;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClients() {
        return clients;
    }

    public void setClients(Client clients) {
        this.clients = clients;
    }

    public Realtor getRealtor() {
        return realtor;
    }

    public void setRealtor(Realtor realtor) {
        this.realtor = realtor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}

