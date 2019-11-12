package com.example.usersapi.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "email")
    private String userName;
    @Column(name = "password")
    private String firstName;
    @Column(name = "username")
    private String lastName;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Profile profile;

    public User() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("User{")
                .append("id:").append(id)
                .append(",userName:").append(userName)
                .append(",firstName:").append(firstName)
                .append(",lastName:").append(lastName)
                .append("}");
        return s.toString();
    }
}
