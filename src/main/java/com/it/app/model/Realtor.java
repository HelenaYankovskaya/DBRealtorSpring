package com.it.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Class for creating the table Realtor
 */
@Entity
@Table
public class Realtor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull(message = "{realtor.post.notNull}")
    @NotEmpty(message = "{realtor.post.notEmpty}")
    @Size(min = 3, max = 20, message = "{realtor.post.size}")
    private String post;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns(@JoinColumn(name = "user_Id"))
    @NotNull(message = "{realtor.user.notNull}")
    private User user;

    @OneToMany(mappedBy = "realtor")
    private Set<Contracts> contracts;

    public Realtor() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Contracts> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contracts> contracts) {
        this.contracts = contracts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

}
