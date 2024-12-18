package uz.praktikum.springboot.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Entity
public class Detail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(length = 100, nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(length = 100, nullable = false)
    private String lastName;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(length = 1, nullable = false)
    private String middleName;

    @NotNull
    @Min(2)
    @Max(100)
    private Integer age;

    @NotNull
    @Size(min = 5, max = 255)
    @Column(length = 255, nullable = false)
    private String address;


    @NotNull
    @Column(nullable = false)
    private Boolean activated = false;

    @OneToOne
    @JoinColumn(name = "passport_id", unique = true, nullable = false)
    private Passport passport;

    @CreationTimestamp
    private Instant createdAt;


    @UpdateTimestamp
    private Instant updatedAt;

    public Detail(String firstName, String lastName, String middleName, Integer age, String address, Boolean activated, Passport passport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.age = age;
        this.address = address;
        this.activated = activated;
        this.passport = passport;
    }

    public Detail() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public @NotNull Boolean getActivated() {
        return activated;
    }

    public void setActivated(@NotNull Boolean activated) {
        this.activated = activated;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }


    public @NotNull @Size(min = 2, max = 100) String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(@NotNull @Size(min = 2, max = 100) String middleName) {
        this.middleName = middleName;
    }

    public @NotNull @Min(2) @Max(100) Integer getAge() {
        return age;
    }

    public void setAge(@NotNull @Min(2) @Max(100) Integer age) {
        this.age = age;
    }

    public @NotNull @Size(min = 5, max = 255) String getAddress() {
        return address;
    }

    public void setAddress(@NotNull @Size(min = 5, max = 255) String address) {
        this.address = address;
    }
}
