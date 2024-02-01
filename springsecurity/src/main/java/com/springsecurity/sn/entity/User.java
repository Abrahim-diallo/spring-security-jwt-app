package com.springsecurity.sn.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, unique = true)
    @NotBlank(message = "First Name is required")
    @Length(min = 2, max = 45, message = "First Name must be between 2 and 45 characters")
    private String FirstName;


    @Column(length = 45, nullable = false)
    @NotBlank(message = "Last Name is required")
    @Length(min = 2, max = 40, message = "Last Name must be between 2 and 45 characters")
    private String lastName;


    @Column(length = 45, nullable = false, unique = true)
    @NotBlank(message = "E-mail  is required")
    @Length(min = 5, max = 125, message = "E-mail must have between 5-125 characters")
    @Email(message = "Email is not valid")
    private String email;

    @Column(length = 40, nullable = false)
    @NotBlank(message = "Password is required")
    @Length(min = 5, max = 64, message = "Password must have between 6 and 64 characters")
    private String password;


    private boolean isActive;


    private boolean isNonlocked;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


    public User() {
    }

    public User(Integer id, String firstName, String lastName, String email, String password, boolean isActive, boolean isNonlocked, Role roles) {
        this.id = id;
        FirstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.isNonlocked = isNonlocked;
        this.roles.add(roles);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isNonlocked() {
        return isNonlocked;
    }

    public void setNonlocked(boolean nonlocked) {
        isNonlocked = nonlocked;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", FirstName='" + FirstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", isNonlocked=" + isNonlocked +
                ", roles=" + roles +
                '}';
    }
}
