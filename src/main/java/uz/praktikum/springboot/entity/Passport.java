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

@Entity
public class Passport implements Serializable {
    public Passport(String firstName, String lastName, String middleName, Integer age,
                    String address, String passportSerial, String passportNumber,
                    String JSHSHIR, String nation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.age = age;
        this.address = address;
        this.passportSerial = passportSerial;
        this.passportNumber = passportNumber;
        this.JSHSHIR = JSHSHIR;
        this.nation = nation;
    }

    public Passport() {

    }

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
    @Size(min = 1, max = 5)
    @Column(length = 5,  nullable = false)
    private String passportSerial;

    @NotNull
    @Size(min = 5, max = 10)
    @Column(length = 5,  nullable = false)
    private String passportNumber;

    @NotNull
    @Size(min = 14, max = 16)
    @Column(length = 20,  nullable = false)
    private String JSHSHIR;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(length = 20,  nullable = false)
    private String nation;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;


    public Long getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public @NotNull @Size(min = 1, max = 5) String getPassportSerial() {
        return passportSerial;
    }

    public void setPassportSerial(@NotNull @Size(min = 1, max = 5) String passportSerial) {
        this.passportSerial = passportSerial;
    }

    public @NotNull @Size(min = 5, max = 10) String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(@NotNull @Size(min = 5, max = 10) String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public @NotNull @Size(min = 14, max = 16) String getJSHSHIR() {
        return JSHSHIR;
    }

    public void setJSHSHIR(@NotNull @Size(min = 14, max = 16) String JSHSHIR) {
        this.JSHSHIR = JSHSHIR;
    }

    public @NotNull @Size(min = 2, max = 20) String getNation() {
        return nation;
    }

    public void setNation(@NotNull @Size(min = 2, max = 20) String nation) {
        this.nation = nation;
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
